package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UserRolesResource {

	String URL = "/user/roles/{id}";
	
	/**
	 * ads
	 * @param entity asd.
	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation put(JsonRepresentation entity);
}
