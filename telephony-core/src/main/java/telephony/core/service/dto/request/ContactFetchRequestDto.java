package telephony.core.service.dto.request;

import telephony.core.query.filter.ContactFilterCriteria;

public class ContactFetchRequestDto extends AuthRequestDto {

	ContactFilterCriteria filters;

	public ContactFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(ContactFilterCriteria filters) {
		this.filters = filters;
	}
}
