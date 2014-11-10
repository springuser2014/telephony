package telephony.core.service.dto.request;

public class DeliveryDeleteRequestDto extends AuthRequestDto {
	
	private Long deliveryId;

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}
}
