package telephony.ws.resource.contact.impl;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.entity.jpa.Contact;
import telephony.core.service.ContactService;
import telephony.core.service.StoreService;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.bean.BasicResponse;
import telephony.ws.resource.contact.ContactsDeleteResource;

/**
 * asd.
 */
public class ContactsDeleteResourceImpl extends TelephonyServerResource
	implements ContactsDeleteResource {
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private ContactService contactService;


	/**
	 * {@inheritDoc}  
	 */
	@Post("json")
	public JsonRepresentation delete(JsonRepresentation entity) 
			throws JSONException, IOException, SessionServiceException, ContactServiceException {
    	
		logger.info("ContactsDeleteResource.delete starts");
		
	
		JSONObject req = entity.getJsonObject();
		
		String username = req.getString("username");
		String sessionId = req.getString("sessionId");
		logger.info(" username = {} ", username);
		logger.info(" sessionId = {} ", sessionId);
		
		Long contactToDeleteId = req.getLong("contactToDeleteId");

		BasicResponse resp = new BasicResponse(true, "Usunieto obiekt");
		try {
			Contact contactToDelete = contactService.findById(
					username, sessionId, contactToDeleteId
			);
			
			contactService.deleteContact(username, sessionId, contactToDelete);
			
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			
			resp.setSuccess(false);
			resp.setMessage("Error occurred");
			return new JsonRepresentation(resp);
		}
		
		getResponse().setStatus(Status.SUCCESS_OK);
		
		return new JsonRepresentation(resp);		
    }
}
