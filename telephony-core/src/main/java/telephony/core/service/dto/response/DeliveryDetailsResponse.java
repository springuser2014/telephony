package telephony.core.service.dto.response;

import telephony.core.service.dto.DeliveryDto;

public class DeliveryDetailsResponse extends BasicResponse {
	
	private DeliveryDto delivery;

	public DeliveryDto getDelivery() {
		return delivery;
	}

	public void setDelivery(DeliveryDto delivery) {
		this.delivery = delivery;
	}
	
}
