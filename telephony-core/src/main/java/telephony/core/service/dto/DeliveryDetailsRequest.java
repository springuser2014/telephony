package telephony.core.service.dto;

public class DeliveryDetailsRequest extends AuthBean {
	
	private Long deliveryId;

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}
}
