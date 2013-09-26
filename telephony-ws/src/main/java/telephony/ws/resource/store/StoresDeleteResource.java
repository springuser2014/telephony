package telephony.ws.resource.store;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoresDeleteResource {
	
	String URL = "/stores/delete/{id}";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);

}