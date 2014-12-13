package telephony.core.service.dto.request;

public class ContactDeleteRequest extends AuthRequest {
	
	private Long contactToDelete;

	public Long getContactToDelete() {
		return contactToDelete;
	}

	public void setContactToDelete(Long contactToDelete) {
		this.contactToDelete = contactToDelete;
	}
}