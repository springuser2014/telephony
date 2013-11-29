package telephony.ws.resource.bean;

import telephony.ws.resource.bean.SessionBean;
import telephony.ws.resource.bean.ContactBean;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class AddContactRequest {
	
	private ContactBean newContact = null;
	private String username = null;
	private String sessionId = null;
	
	/**
	 * asd.
	 * @param sessionBean asd.
	 * @param contactBean asd.
	 */
	public AddContactRequest(SessionBean sessionBean, ContactBean contactBean) {
		this.username = sessionBean.getUsername();
		this.sessionId = sessionBean.getSessionId();			
		this.newContact = contactBean;
	}

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 */
	public AddContactRequest(String username, String sessionId) {
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
	public AddContactRequest(String username, String sessionId, ContactBean newContact) {
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
