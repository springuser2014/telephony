package telephony.ws.resource.bean;

/**
 * asd.
 */
public class DeleteRoleRequest {
	
	private String username = null;
	private String sessionId = null;
			
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId sad.
	 * @param label ads.
	 */
	public DeleteRoleRequest(String username, String sessionId) {
		this.username = username;
		this.sessionId = sessionId;
	}
	
	/**
	 * asd.
	 * @param sessionBean ads.
	 * @param label asd.
	 */
	public DeleteRoleRequest(SessionBean sessionBean, String label) {
		this.username = sessionBean.getUsername();
		this.sessionId = sessionBean.getSessionId();
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
