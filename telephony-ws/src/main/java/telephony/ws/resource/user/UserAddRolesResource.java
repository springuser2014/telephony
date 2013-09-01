package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UserAddRolesResource {

	String URL = "/user/addRoles/{id}";
	
	/**
	 * ads.
	 * @param entity asd.
	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation add(JsonRepresentation entity);
}
