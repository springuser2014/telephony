package telephony.core.service.dto.request;

import telephony.core.query.filter.ContactFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class ContactFetchRequest extends AuthRequest {

	ContactFilterCriteria filters;

	public ContactFetchRequest() {
		super();
	}

	public ContactFetchRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public ContactFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(ContactFilterCriteria filters) {
		this.filters = filters;
	}
}
