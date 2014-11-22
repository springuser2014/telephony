package telephony.core.service.dto.response;

import java.util.List;

import telephony.core.service.dto.ContactDto;
/**
 * asd.
 */
public class ContactListResponse extends BasicResponse {
	
	private List<ContactDto> contacts;
	
	/**
	 * asd.
	 */
	public ContactListResponse() {
		
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public List<ContactDto> getContacts() {
		return contacts;
	}

	/**
	 * asd.
	 * @param contacts asd.
	 */
	public void setContacts(List<ContactDto> contacts) {
		this.contacts = contacts;
	}
	
}
