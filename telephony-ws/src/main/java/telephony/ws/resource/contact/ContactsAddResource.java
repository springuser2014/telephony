package telephony.ws.resource.contact;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

import telephony.core.service.dto.ContactAddRequestDto;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface ContactsAddResource {
	
	String URL = "/contacts/add";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws ContactServiceException 
	 * @throws SessionServiceException 
	 */
	@Post("json")
	JsonRepresentation add(ContactAddRequestDto entity) 
			throws JSONException, IOException, SessionServiceException, ContactServiceException;

}