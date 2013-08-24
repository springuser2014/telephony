package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface DeliveriesResource {
	String URL = "/deliveries";
	
	/**
	 * asd.
	 * @param entity asd.
 	 * @return asd.
	 */
	@Get("json")
	JsonRepresentation get(JsonRepresentation entity);
	
	/**
	 * asd.
	 * @param entity asd.
	 * @return ads.
	 */
	@Post("json")
	JsonRepresentation post(JsonRepresentation entity);
}
