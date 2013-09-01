package telephony.ws.resource.session;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SessionDestroyResource {
	
	String URL = "/session/destroy";

	/**
	 * asd.
	 * @param entity asd. 
	 * @return asd.
	 * @throws JSONException 
	 * @throws IOException 
	 */
	@Delete("json")
	JsonRepresentation destroy(JsonRepresentation entity) throws IOException,
			JSONException;

}