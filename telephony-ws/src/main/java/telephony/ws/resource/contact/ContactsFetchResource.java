package telephony.ws.resource.contact;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import telephony.core.service.dto.request.ContactFetchRequest;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

import java.io.IOException;

/**
 * ads.
 */
public interface ContactsFetchResource {

	String URL = "/contacts/fetch";
	
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
	JsonRepresentation fetch(ContactFetchRequest entity)
			throws JSONException, IOException, SessionServiceException, ContactServiceException;

}