package telephony.core.service.dto.request;

import telephony.core.query.filter.DeliveryFilterCriteria;

public class DeliveriesFetchRequest extends AuthRequest {

	DeliveryFilterCriteria filters;

	public DeliveryFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(DeliveryFilterCriteria filters) {
		this.filters = filters;
	}
}
