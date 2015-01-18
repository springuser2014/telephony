package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ContactDeleteRequest extends AuthRequest {
	
	private Long contactToDelete;

	public ContactDeleteRequest() {
		super();
	}

	public ContactDeleteRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public Long getContactToDelete() {
		return contactToDelete;
	}

	public void setContactToDelete(Long contactToDelete) {
		this.contactToDelete = contactToDelete;
	}
}
