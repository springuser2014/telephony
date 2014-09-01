package telephony.ws.resource.contact;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * Some stupid javadoc
 * <pre>
 *  {@code
 *  {
 *  	"sessionId" : "###",
 *  	"username" : "###",
 *  	"contactToDeleteId" : ###
 *  }
 * </pre>
 */
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
	JsonRepresentation delete(JsonRepresentation entity) 
			throws JSONException, IOException, SessionServiceException, ContactServiceException;

}