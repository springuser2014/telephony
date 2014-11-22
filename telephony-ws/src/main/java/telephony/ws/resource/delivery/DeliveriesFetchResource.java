package telephony.ws.resource.delivery;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

import telephony.core.service.dto.request.DeliveriesFetchRequest;

/**
 * asd.
 */
public interface DeliveriesFetchResource {
	
	String URL = "/deliveries/fetch";
	
	/**
	 * asd.
	 * @param entity asd.
 	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation fetch(DeliveriesFetchRequest entity);
}
