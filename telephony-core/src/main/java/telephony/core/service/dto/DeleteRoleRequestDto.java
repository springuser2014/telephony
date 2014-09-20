package telephony.core.service.dto;

/**
 * asd.
 */
public class DeleteRoleRequestDto extends AuthDto {
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId sad.
	 * @param label ads.
	 */
	public DeleteRoleRequestDto(String username, String sessionId) {
		this.setUsername(username);
		this.setSessionId(sessionId);
	}
	
	/**
	 * asd.
	 * @param sessionBean ads.
	 * @param label asd.
	 */
	public DeleteRoleRequestDto(SessionBean sessionBean, String label) {
		this.setUsername(sessionBean.getUsername());
		this.setSessionId(sessionBean.getSessionId());
	}
}
