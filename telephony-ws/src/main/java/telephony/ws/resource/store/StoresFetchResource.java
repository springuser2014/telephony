package telephony.ws.resource.store;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoresFetchResource {
	
	String URL = "/stores/fetch";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation list(JsonRepresentation entity);

}