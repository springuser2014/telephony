package telephony.core.service.dto.request;

import telephony.core.query.filter.UserFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class UsersFetchRequest extends AuthRequest {

	private UserFilterCriteria filters;

	public UsersFetchRequest() {
		super();
	}

	public UsersFetchRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public UserFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(UserFilterCriteria filters) {
		this.filters = filters;
	}
}
