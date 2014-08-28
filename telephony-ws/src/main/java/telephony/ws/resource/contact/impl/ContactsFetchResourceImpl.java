package telephony.ws.resource.contact.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.entity.jpa.Contact;
import telephony.core.service.ContactService;
import telephony.core.service.dto.ContactBean;
import telephony.core.service.dto.ContactListResponse;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsFetchResource;

import com.google.inject.Inject;

import static telephony.core.service.dto.ContactBean.create;

/**
 * asd.
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
	public JsonRepresentation fetch(JsonRepresentation entity) 
			throws JSONException, IOException, SessionServiceException, ContactServiceException {
        
		logger.info("ContactsFetchResource.fetch starts");
		JSONObject req = new JsonRepresentation(entity).getJsonObject();
		
		String name = req.getString("username");
		String sessionId = req.getString("sessionId");
		
		logger.info(" username = {} ", name);
        logger.info(" sessionId = {} ", sessionId);
        
        List<Contact> contacts = contactService.find(null, null);       
        List<ContactBean> contactsToJsonize = convertToBeans(contacts);
        
        ContactListResponse response = new ContactListResponse();
        response.setContacts(contactsToJsonize);
        
        return new JsonRepresentation(response);        
    }


	private List<ContactBean> convertToBeans(List<Contact> contacts) {
		
		List<ContactBean> contactsToJsonize =  new ArrayList<ContactBean>();
        
        for (Contact c : contacts) {
        	contactsToJsonize.add(create(c));
        }
        
		return contactsToJsonize;
	}
}
