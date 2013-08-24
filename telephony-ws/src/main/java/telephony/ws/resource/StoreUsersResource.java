package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoreUsersResource {
	
	String URL = "/store/users/{id}";

	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 */
	@Get("json")
	JsonRepresentation list(JsonRepresentation entity);

}