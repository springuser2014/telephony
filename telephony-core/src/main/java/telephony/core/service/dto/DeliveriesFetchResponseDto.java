package telephony.core.service.dto;

import java.util.List;

public class DeliveriesFetchResponseDto {

	private List<DeliveryDto> deliveries;

	public List<DeliveryDto> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<DeliveryDto> deliveries) {
		this.deliveries = deliveries;
	}
	
	public void addDelivery(DeliveryDto bean) {
		
		if (!this.deliveries.contains(bean)) {
			this.deliveries.add(bean);
		}
	}
	
	public void removeDelivery(DeliveryDto bean) {
		
		if (this.deliveries.contains(bean)) {
			this.deliveries.remove(bean);
		}
	}
}
