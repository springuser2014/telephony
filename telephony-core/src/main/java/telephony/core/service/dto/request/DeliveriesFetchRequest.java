package telephony.core.service.dto.request;

import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class DeliveriesFetchRequest extends AuthRequest {

	DeliveryFilterCriteria filters;

	public DeliveriesFetchRequest() {}

	public DeliveriesFetchRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public DeliveryFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(DeliveryFilterCriteria filters) {
		this.filters = filters;
	}
}
