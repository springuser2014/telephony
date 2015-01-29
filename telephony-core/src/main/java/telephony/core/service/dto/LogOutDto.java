package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogOutDto {

	private String username;
	private String sessionId;
	
	public static LogOutDto create() {
		return new LogOutDto();
	}

	public String username() {
		return username;
	}

	public LogOutDto username(String username) {
		this.username = username;
		return this;
	}

	public String password() {
		return sessionId;
	}

	public LogOutDto password(String password) {
		this.sessionId = password;
		return this;
	}

}
