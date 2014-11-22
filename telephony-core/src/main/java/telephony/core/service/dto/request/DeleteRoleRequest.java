package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionBean;

/**
 * asd.
 */
public class DeleteRoleRequest extends AuthRequest {
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId sad.
	 * @param label ads.
	 */
	public DeleteRoleRequest(String username, String sessionId) {
		this.setUsername(username);
		this.setSessionId(sessionId);
	}
	
	/**
	 * asd.
	 * @param sessionBean ads.
	 * @param label asd.
	 */
	public DeleteRoleRequest(SessionBean sessionBean, String label) {
		this.setUsername(sessionBean.getUsername());
		this.setSessionId(sessionBean.getSessionId());
	}
}
