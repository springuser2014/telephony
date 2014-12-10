package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class AuthRequest {

	private String username;
	private String sessionId;

	public AuthRequest() {
		this.username = "";
		this.sessionId = "";
	}

	public AuthRequest(SessionDto sessionDto) {
		this.username = sessionDto.getUsername();
		this.sessionId = sessionDto.getSessionId();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
