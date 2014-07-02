package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

/**
 * asd.
 */
public interface UsersEditResource {
	
	String URL = "/users/edit/{id}";

	/**
	 * asd. 
	 * @param entity asd.
	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation edit(JsonRepresentation entity);
}
