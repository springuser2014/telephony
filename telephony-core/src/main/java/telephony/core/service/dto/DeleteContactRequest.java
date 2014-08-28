package telephony.core.service.dto;


/**
 * asd.
 */
public class DeleteContactRequest {
	
	private Long contactToDeleteId = null;
	private String username = null;
	private String sessionId = null;
	
	/**
	 * asd.
	 * @param sessionBean asd.
	 * @param contactToDeleteId asd.
	 */
	public DeleteContactRequest(SessionBean sessionBean, Long contactToDeleteId) {
		this.username = sessionBean.getUsername();
		this.sessionId = sessionBean.getSessionId();			
		this.contactToDeleteId = contactToDeleteId;
	}
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param contactToDeleteId asd.
	 */
	public DeleteContactRequest(String username, String sessionId, Long contactToDeleteId) {
		this.username = username;
		this.sessionId = sessionId;			
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
