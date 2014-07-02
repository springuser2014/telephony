package telephony.ws.resource.contact.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.PersistenceException;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Sale;
import telephony.core.service.ContactService;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.bean.BasicResponse;
import telephony.ws.resource.contact.ContactsAddResource;


/**
 * sahdhsaudh sauhdai
 * 
 * <pre>
 *  {@code
 *  	"sessionId" : "###",
 *  	"username" : "###",
 *  	"newContact" : {
 *  		"label" : "###",
 *  		"details" : "###"
 *  	} 
 *  
 *  }
 * </pre>
 */
public class ContactsAddResourceImpl extends TelephonyServerResource implements
		ContactsAddResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ContactService contactService;

	/**
	 * {@inheritDoc}
	 */
	@Override
    @Post("json")
	public JsonRepresentation add(JsonRepresentation entity) 
			throws JSONException, IOException, SessionServiceException, ContactServiceException {
    	
		logger.info("ContactsAddResource.add starts");		
		JSONObject req = new JsonRepresentation(entity).getJsonObject();
		
		String username = req.getString("username");
		String sessionId = req.getString("sessionId");
		
		JSONObject newContactJson = req.getJSONObject("newContact");
		
		String details = newContactJson.getString("details"); 
		String label = newContactJson.getString("label");
				
		Contact newContact = new Contact();
		newContact.setDetails(details);
		newContact.setLabel(label);
		newContact.setDeliveries(new ArrayList<Delivery>());
		newContact.setSales(new ArrayList<Sale>());
		
		// TODO : dodac zaciaganie jezyków z properties
		BasicResponse response = new BasicResponse(true, "Dodano sukcesywnie");
		
		try {
			contactService.addNewContact(username, sessionId, newContact);
			
		} catch (Exception ex) { // TODO : dodac rozróżnienie na różne typy wyjątków
			response.setMessage("Wystapił błąd podczas dodawania");
			response.setSuccess(false);
		}
		
		return new JsonRepresentation(response);
    }

}
