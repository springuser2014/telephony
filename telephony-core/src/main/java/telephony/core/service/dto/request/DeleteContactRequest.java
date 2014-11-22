package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionBean;


/**
 * asd.
 */
public class DeleteContactRequest extends AuthRequest {
	
	private Long contactToDeleteId = null;
	
	/**
	 * asd.
	 * @param sessionBean asd.
	 * @param contactToDeleteId asd.
	 */
	public DeleteContactRequest(SessionBean sessionBean, Long contactToDeleteId) {
		this.setUsername(sessionBean.getUsername() );
		this.setSessionId(sessionBean.getSessionId());			
		this.contactToDeleteId = contactToDeleteId;
	}
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param contactToDeleteId asd.
	 */
	public DeleteContactRequest(String username, String sessionId, Long contactToDeleteId) {
		this.setUsername(username);
		this.setSessionId(sessionId);			
		this.contactToDeleteId = contactToDeleteId;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Long getContactToDeleteId() {
		return contactToDeleteId;
	}

	/**
	 * asd.
	 * @param contactToDeleteId asd.
	 */
	public void setContactToDeleteId(Long contactToDeleteId) {
		this.contactToDeleteId = contactToDeleteId;
	}
}
