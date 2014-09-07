package telephony.core.service.dto;

import telephony.core.query.filter.DeliveryFilterCriteria;

public class DeliveriesFetchRequest extends AuthBean {

	DeliveryFilterCriteria filters;

	public DeliveryFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(DeliveryFilterCriteria filters) {
		this.filters = filters;
	}
}
