package telephony.ws.resource.delivery;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface DeliveriesAddResource {

	String URL = "/deliveries/add";
	
	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation add(JsonRepresentation entity);
}
