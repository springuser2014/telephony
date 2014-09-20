package telephony.core.service.dto;

import telephony.core.query.filter.DeliveryFilterCriteria;

public class DeliveriesFetchRequestDto extends AuthDto {

	DeliveryFilterCriteria filters;

	public DeliveryFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(DeliveryFilterCriteria filters) {
		this.filters = filters;
	}
}
