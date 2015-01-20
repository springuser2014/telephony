package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class DeliveryDetailsRequest extends AuthRequest {
	
	private Long deliveryId;

	public DeliveryDetailsRequest() {
		super();
	}

	public DeliveryDetailsRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}
}
