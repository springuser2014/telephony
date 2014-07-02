package telephony.ws.resource.bean;

/**
 * asd.
 */
public class DeleteRoleRequest {
	
	private String label = null;
	private String username = null;
	private String sessionId = null;
			
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId sad.
	 * @param label ads.
	 */
	public DeleteRoleRequest(String username, String sessionId, String label) {
		this.username = username;
		this.sessionId = sessionId;
		this.label = label;
	}
	
	/**
	 * asd.
	 * @param sessionBean ads.
	 * @param label asd.
	 */
	public DeleteRoleRequest(SessionBean sessionBean, String label) {
		this.username = sessionBean.getUsername();
		this.sessionId = sessionBean.getSessionId();
		this.label = label;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * asd.
	 * @param label asd.
	 */
	public void setLabel(String label) {
		this.label = label;
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
