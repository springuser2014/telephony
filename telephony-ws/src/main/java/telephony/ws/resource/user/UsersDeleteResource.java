package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UsersDeleteResource {

	String URL = "/users/delete/{id}";
			
	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);

}