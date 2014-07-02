package telephony.ws.resource.role;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

/**
 * asd.
 */
public interface RolesAddResource {
	
	String URL = "/roles/add";

	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@Post("json")
	JsonRepresentation add(JsonRepresentation entity) throws JSONException, IOException;

}