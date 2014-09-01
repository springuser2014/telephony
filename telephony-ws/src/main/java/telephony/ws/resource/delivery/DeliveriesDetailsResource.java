package telephony.ws.resource.delivery;

import telephony.core.service.dto.DeliveryDetailsRequest;
import telephony.core.service.dto.DeliveryDetailsResponse;

public interface DeliveriesDetailsResource {

	String URL = "/deliveries/details";
	
	DeliveryDetailsResponse details(DeliveryDetailsRequest request);
}
