package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionBean;

/**
 * asd.
 */
public class RoleAddRequest extends AuthRequest {
	
	private String label;
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId sad.
	 * @param label ads.
	 */
	public RoleAddRequest(String username, String sessionId, String label) {
		this.setUsername(username);
		this.setSessionId(sessionId);
		this.label = label;
	}
	
	/**
	 * asd.
	 * @param sessionBean ads.
	 * @param label asd.
	 */
	public RoleAddRequest(SessionBean sessionBean, String label) {
		this.setUsername(sessionBean.getUsername());
		this.setSessionId(sessionBean.getSessionId());
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
}
