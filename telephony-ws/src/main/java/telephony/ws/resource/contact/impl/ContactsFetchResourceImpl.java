package telephony.ws.resource.contact.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.entity.jpa.Contact;
import telephony.core.service.ContactService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsFetchResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
public class ContactsFetchResourceImpl extends TelephonyServerResource
	implements ContactsFetchResource {
	

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ContactService contactService;


	/**
	 * {@inheritDoc}
	 */
	@Post("json")
	public final JsonRepresentation fetch(JsonRepresentation entity) 
			throws JSONException, IOException {
        
		logger.info("fetch starts");
		JSONObject req = new JsonRepresentation(entity).getJsonObject();
		
		String name = req.getString("username");
		String sessionId = req.getString("sessionId");
		
		logger.info(" username = {} ", name);
        logger.info(" sessionId = {} ", sessionId);
        
        List<Contact> contacts = new ArrayList<Contact>();
        try {
        	contacts = contactService.fetchAll(name, sessionId);
        	
        }  catch (Exception e) {
        	logger.error("Error occured during session initialization", e);
        }
        
        List<telephony.ws.resource.bean.Contact> contactsToJsonize = 
        		new ArrayList<telephony.ws.resource.bean.Contact>();
        
        for (Contact c : contacts) {
        	contactsToJsonize.add(telephony.ws.resource.bean.Contact.create(c));
        }
        
        return new JsonRepresentation(contactsToJsonize);
        
    }
}
