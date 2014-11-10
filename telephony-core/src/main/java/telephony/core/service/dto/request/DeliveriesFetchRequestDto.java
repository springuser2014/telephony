package telephony.core.service.dto.request;

import telephony.core.query.filter.DeliveryFilterCriteria;

public class DeliveriesFetchRequestDto extends AuthRequestDto {

	DeliveryFilterCriteria filters;

	public DeliveryFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(DeliveryFilterCriteria filters) {
		this.filters = filters;
	}
}
