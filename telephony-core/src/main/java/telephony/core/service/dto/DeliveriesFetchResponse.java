package telephony.core.service.dto;

import java.util.List;


public class DeliveriesFetchResponse {

	private List<DeliveryBean> deliveries;

	public List<DeliveryBean> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<DeliveryBean> deliveries) {
		this.deliveries = deliveries;
	}
	
	public void addDelivery(DeliveryBean bean) {
		
		if (!this.deliveries.contains(bean)) {
			this.deliveries.add(bean);
		}
	}
	
	public void removeDelivery(DeliveryBean bean) {
		
		if (this.deliveries.contains(bean)) {
			this.deliveries.remove(bean);
		}
	}
}
