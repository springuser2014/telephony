package telephony.ws.resource.delivery;

import telephony.core.service.dto.request.DeliveryDetailsRequest;
import telephony.core.service.dto.response.DeliveryDetailsResponse;
import telephony.core.service.exception.SessionServiceException;

public interface DeliveriesDetailsResource {

	String URL = "/deliveries/details";
	
	DeliveryDetailsResponse details(DeliveryDetailsRequest request) throws SessionServiceException;
}
