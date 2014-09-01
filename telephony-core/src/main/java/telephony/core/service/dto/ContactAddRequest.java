package telephony.core.service.dto;

import telephony.core.service.dto.ContactBean;
import telephony.core.service.dto.SessionBean;

/**
 * asd.
 */
public class ContactAddRequest {
	
	private ContactBean newContact = null;
	private String username = null;
	private String sessionId = null;
	
	/**
	 * asd.
	 * @param sessionBean asd.
	 * @param contactBean asd.
	 */
	public ContactAddRequest(SessionBean sessionBean, ContactBean contactBean) {
		this.username = sessionBean.getUsername();
		this.sessionId = sessionBean.getSessionId();			
		this.newContact = contactBean;
	}

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 */
	public ContactAddRequest(String username, String sessionId) {
		this.username = username;
		this.sessionId = sessionId;
		this.newContact = null;
	}
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param newContact asda.
	 */
	public ContactAddRequest(String username, String sessionId, ContactBean newContact) {
		this.username = username;
		this.sessionId = sessionId;
		this.newContact = newContact;
	}

	/**
	 * asd.
	 * @return asd.
 	 */
	public ContactBean getNewContact() {
		return newContact;
	}

	/**
	 * asd.
	 * @param newContact asd.
	 */
	public void setNewContact(ContactBean newContact) {
		this.newContact = newContact;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * asd.
	 * @param username asd.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * asd.
	 * @param sessionId asd.
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
