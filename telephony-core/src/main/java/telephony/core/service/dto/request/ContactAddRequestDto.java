package telephony.core.service.dto.request;

import telephony.core.service.dto.ContactDto;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public class ContactAddRequestDto extends AuthRequestDto {
	
	private ContactDto newContact = null;
	/**
	 * asd.
	 * @param sessionBean asd.
	 * @param contactBean asd.
	 */
	public ContactAddRequestDto(SessionDto sessionBean, ContactDto contactBean) {
		this.setUsername(sessionBean.getUsername());
		this.setSessionId(sessionBean.getSessionId());			
		this.newContact = contactBean;
	}

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 */
	public ContactAddRequestDto(String username, String sessionId) {
		this.setUsername(username);
		this.setSessionId(sessionId);
		this.newContact = null;
	}
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param newContact asda.
	 */
	public ContactAddRequestDto(String username, String sessionId, ContactDto newContact) {
		this.setUsername(username);
		this.setSessionId(sessionId);
		this.newContact = newContact;
	}

	/**
	 * asd.
	 * @return asd.
 	 */
	public ContactDto getNewContact() {
		return newContact;
	}

	/**
	 * asd.
	 * @param newContact asd.
	 */
	public void setNewContact(ContactDto newContact) {
		this.newContact = newContact;
	}	
}
