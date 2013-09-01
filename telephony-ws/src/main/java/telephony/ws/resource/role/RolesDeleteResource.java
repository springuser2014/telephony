package telephony.ws.resource.role;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface RolesDeleteResource {
	
	String URL = "/roles/{id}";
	
	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);
	
}
