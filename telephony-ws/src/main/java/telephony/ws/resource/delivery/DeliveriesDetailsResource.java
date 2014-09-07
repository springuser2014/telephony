package telephony.ws.resource.delivery;

import telephony.core.service.dto.DeliveryDetailsRequest;
import telephony.core.service.dto.DeliveryDetailsResponse;
import telephony.core.service.exception.SessionServiceException;

public interface DeliveriesDetailsResource {

	String URL = "/deliveries/details";
	
	DeliveryDetailsResponse details(DeliveryDetailsRequest request) throws SessionServiceException;
}
