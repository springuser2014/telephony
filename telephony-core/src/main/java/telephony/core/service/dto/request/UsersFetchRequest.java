package telephony.core.service.dto.request;

import telephony.core.query.filter.UserFilterCriteria;

public class UsersFetchRequest extends AuthRequestDto {

	private UserFilterCriteria filters;

	public UserFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(UserFilterCriteria filters) {
		this.filters = filters;
	}
}
