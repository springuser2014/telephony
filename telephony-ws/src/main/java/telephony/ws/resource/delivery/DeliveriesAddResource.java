package telephony.ws.resource.delivery;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

import telephony.core.service.dto.DeliveryAddRequestDto;

/**
 * asd.
 */
public interface DeliveriesAddResource {

	String URL = "/deliveries/add";
	
	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation add(DeliveryAddRequestDto entity);
}
