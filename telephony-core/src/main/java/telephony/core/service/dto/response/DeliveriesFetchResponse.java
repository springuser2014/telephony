package telephony.core.service.dto.response;

import telephony.core.service.dto.DeliverySearchDto;

import java.util.List;

public class DeliveriesFetchResponse extends BasicResponse {

	private List<DeliverySearchDto> deliveries;
	private Long countTotal;

	public List<DeliverySearchDto> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<DeliverySearchDto> deliveries) {
		this.deliveries = deliveries;
	}
	
	public void addDelivery(DeliverySearchDto bean) {
		
		if (!this.deliveries.contains(bean)) {
			this.deliveries.add(bean);
		}
	}
	
	public void removeDelivery(DeliverySearchDto bean) {
		
		if (this.deliveries.contains(bean)) {
			this.deliveries.remove(bean);
		}
	}

	public Long getCountTotal() {
		return countTotal;
	}

	public void setCountTotal(Long countTotal) {
		this.countTotal = countTotal;
	}
}
