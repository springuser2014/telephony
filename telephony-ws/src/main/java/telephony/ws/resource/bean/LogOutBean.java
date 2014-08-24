package telephony.ws.resource.bean;

public class LogOutBean {

	private String username;
	private String sessionId;
	
	public static LogOutBean create() {
		return new LogOutBean();
	}

	public String username() {
		return username;
	}

	public LogOutBean username(String username) {
		this.username = username;
		return this;
	}

	public String password() {
		return sessionId;
	}

	public LogOutBean password(String password) {
		this.sessionId = password;
		return this;
	}

}
