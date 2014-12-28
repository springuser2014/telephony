package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class DeliveryDeleteRequest extends AuthRequest {

	public DeliveryDeleteRequest(SessionDto sessionDto) {
		super(sessionDto);
	}
	
	private Long deliveryId;

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}
}
