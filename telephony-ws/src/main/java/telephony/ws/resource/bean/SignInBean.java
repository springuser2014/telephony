package telephony.ws.resource.bean;


/**
 * asd.
 */
public class SignInBean {
	
	private String username;
	private String password;
	
	public static SignInBean create() {
		return new SignInBean();
	}

	public String username() {
		return username;
	}

	public SignInBean username(String username) {
		this.username = username;
		return this;
	}

	public String password() {
		return password;
	}

	public SignInBean password(String password) {
		this.password = password;
		return this;
	}
}