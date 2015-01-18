package telephony.core.service.dto.request;

import telephony.core.service.dto.ContactDto;
import telephony.core.service.dto.SessionDto;

public class ContactAddRequest extends AuthRequest {
	
	private ContactDto contact = null;

	public ContactAddRequest() {
		super();
	}

	public ContactAddRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public ContactDto getContact() {
		return contact;
	}

	public void setContact(ContactDto contact) {
		this.contact = contact;
	}	
}
