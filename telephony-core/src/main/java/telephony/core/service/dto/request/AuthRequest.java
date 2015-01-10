package telephony.core.service.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import telephony.core.service.dto.SessionDto;

import java.util.Date;

public class AuthRequest extends BasicRequest {

	private String username;
	private String sessionId;

	@JsonIgnore
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
