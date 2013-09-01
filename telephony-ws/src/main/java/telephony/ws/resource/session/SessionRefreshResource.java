package telephony.ws.resource.session;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SessionRefreshResource {

	String URL = "/session/refresh";

	/**
	 * asd. 
	 * @param entity asd.
	 * @return asd.
	 * @throws JSONException 
	 * @throws IOException 
	 */
	@Post("json")
	JsonRepresentation refresh(JsonRepresentation entity) throws IOException,
			JSONException;

}