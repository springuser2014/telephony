package telephony.ws.resource.role;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

/**
 * asd.
 */
public interface RolesDeleteResource {
	
//	String URL = "/roles/delete/{id}";

	String URL = "/roles/delete";
	
	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity) throws JSONException, IOException;
	
}
