package telephony.core.service.dto.request;

import telephony.core.service.dto.ContactEditDto;

public class ContactEditRequest extends AuthRequestDto {
	
	private ContactEditDto contactToEdit;

	public ContactEditDto getContactToEdit() {
		return contactToEdit;
	}

	public void setContactToEdit(ContactEditDto contactToEdit) {
		this.contactToEdit = contactToEdit;
	}
}
