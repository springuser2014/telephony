package telephony.ws.resource.delivery;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

import telephony.core.service.dto.DeliveryDeleteRequestDto;

/**
 * asd.
 */
public interface DeliveriesDeleteResource {
	
	String URL = "/deliveries/delete";

	/**
	 * asd. 
	 * @param entity asd.
	 * @return asd.
	 */
	@Delete("json")
	JsonRepresentation delete(DeliveryDeleteRequestDto entity);

}