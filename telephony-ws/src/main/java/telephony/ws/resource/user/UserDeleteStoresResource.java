package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

/**
 * asd.
 */
public interface UserDeleteStoresResource {
	
	String URL = "/user/deleteStores/id";
	
	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);

}
