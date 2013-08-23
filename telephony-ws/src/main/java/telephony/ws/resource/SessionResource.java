package telephony.ws.resource;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SessionResource {
	
	/**
	 * asd.
	 */
    String URL = "/session";
    
	/**
	 * asd.
	 * @param entity asd. 
	 * @return asd.
	 * @throws IOException 
	 * @throws JSONException 
	 * 
	 */
	@Post("json") 
	JsonRepresentation start(Representation entity) throws JSONException, IOException;
	

	/**
	 * asd.
	 * @param entity asd. 
	 * @return asd.
	 * @throws JSONException 
	 * @throws IOException 
	 */
	@Delete("json") 
	JsonRepresentation end(Representation entity) throws IOException, JSONException;

	/**
	 * asd. 
	 * @param entity asd.
	 * @return asd.
	 * @throws JSONException 
	 * @throws IOException 
	 */
	@Put("json") 
	JsonRepresentation refresh(Representation entity) throws IOException, JSONException;

}
