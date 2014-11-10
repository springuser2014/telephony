package telephony.ws.resource.delivery;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

import telephony.core.service.dto.request.DeliveryEditRequestDto;
import telephony.core.service.dto.response.DeliveryEditResponseDto;

/**
 * asd.
 */
public interface DeliveriesEditResource  {

	String URL = "/deliveries/edit";
	
	/**
	 * asd.
	 * @param entity asd.
 	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation edit(DeliveryEditRequestDto entity);
	
}
