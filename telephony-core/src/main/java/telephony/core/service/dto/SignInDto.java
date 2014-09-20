package telephony.core.service.dto;


/**
 * asd.
 */
// TODO : replace using AuthDto
public class SignInDto {
	
	private String username;
	private String password;
	
	public static SignInDto create() {
		return new SignInDto();
	}

	public String username() {
		return username;
	}

	public SignInDto username(String username) {
		this.username = username;
		return this;
	}

	public String password() {
		return password;
	}

	public SignInDto password(String password) {
		this.password = password;
		return this;
	}
}