package telephony.ws.resource.store;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * asd.
 */
public interface StoreFetchUsersResource {
	
	String URL = "/store/users/{id}";

	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation list(JsonRepresentation entity);

}