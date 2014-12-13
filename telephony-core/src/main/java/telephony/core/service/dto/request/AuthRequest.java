package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

import javax.mail.Session;
import java.util.Date;

public class AuthRequest {

	private String username;
	private String sessionId;
	private Date validation;

	public AuthRequest() {
		this.username = "";
		this.sessionId = "";
		this.validation = new Date(0);
	}

	public AuthRequest(SessionDto sessionDto) {
		this.username = sessionDto.getUsername();
		this.sessionId = sessionDto.getSessionId();
		this.validation = sessionDto.getValidity();
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

	public SessionDto getSessionDto() {
		return SessionDto.create(username, sessionId, validation);
	}
}
