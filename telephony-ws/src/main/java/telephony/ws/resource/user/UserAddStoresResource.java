package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UserAddStoresResource {

	String URL = "/user/addStores/{id}";
	
	/**
	 * ads. 
	 * @param entity asd.
	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation put(JsonRepresentation entity);
}
