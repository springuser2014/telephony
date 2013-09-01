package telephony.ws.resource.store;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoreFetchUsersResource {
	
	String URL = "/store/fetchUsers/{id}";

	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation list(JsonRepresentation entity);

}