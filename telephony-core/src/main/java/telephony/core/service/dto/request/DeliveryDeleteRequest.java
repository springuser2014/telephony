package telephony.core.service.dto.request;

public class DeliveryDeleteRequest extends AuthRequest {
	
	private Long deliveryId;

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}
}