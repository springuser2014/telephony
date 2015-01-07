package telephony.ws.resource.contact;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import telephony.core.service.dto.request.ContactDeleteRequest;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

import java.io.IOException;

public interface ContactsDeleteResource {

	String URL = "/contacts/delete";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws ContactServiceException 
	 * @throws SessionServiceException 
	 */
	@Post("json")
	JsonRepresentation delete(ContactDeleteRequest entity)
			throws JSONException, IOException, SessionServiceException, ContactServiceException;

}