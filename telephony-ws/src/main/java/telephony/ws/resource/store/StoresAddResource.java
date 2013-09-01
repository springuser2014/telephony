package telephony.ws.resource.store;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoresAddResource {
	
	String URL = "/stores/add";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation add(JsonRepresentation entity);

}