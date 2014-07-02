package telephony.ws.resource.session;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;


/**
 * asd.
 */
public interface SessionInitializationResource  {
	
	/**
	 * asd.
	 */
    String URL = "/session/initialize";
    
	/**
	 * asd.
	 * @param entity asd. 
	 * @return asd.
	 * @throws IOException asd.
	 * @throws JSONException asd.
	 * 
	 */
	@Post("json") 
	JsonRepresentation initialize(JsonRepresentation entity) throws JSONException, IOException;

}
