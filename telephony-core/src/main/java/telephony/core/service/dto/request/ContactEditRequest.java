package telephony.core.service.dto.request;

import telephony.core.service.dto.ContactEditDto;
import telephony.core.service.dto.SessionDto;

public class ContactEditRequest extends AuthRequest {
	
	private ContactEditDto contactToEdit;

	public ContactEditRequest() {
		super();
	}

	public ContactEditRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public ContactEditDto getContactToEdit() {
		return contactToEdit;
	}

	public void setContactToEdit(ContactEditDto contactToEdit) {
		this.contactToEdit = contactToEdit;
	}
}
