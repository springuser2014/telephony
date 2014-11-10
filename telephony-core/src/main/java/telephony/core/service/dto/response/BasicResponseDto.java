package telephony.core.service.dto.response;

/**
 * asd.
 */
public class BasicResponseDto {
	
	private boolean success;
	private String  message;
	
	/**
	 * asd.
	 */
	public BasicResponseDto() {
		
	}
	
	/**
	 * asd.
	 * @param isSuccess asd.
	 * @param msg asd.
	 */
	public BasicResponseDto(boolean isSuccess, String msg) {
		this.success = isSuccess;
		this.message = msg;
	}

	/**
	 * 
	 * asd.
	 * @return asd.
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * asd.
	 * @param success asd.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * asd.
	 * @param message asd.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
