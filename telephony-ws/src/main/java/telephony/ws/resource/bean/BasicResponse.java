package telephony.ws.resource.bean;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class BasicResponse {
	
	private boolean success;
	private String  message;
	
	/**
	 * asd.
	 */
	public BasicResponse() {
		
	}
	
	/**
	 * asd.
	 * @param isSuccess asd.
	 * @param msg asd.
	 */
	public BasicResponse(boolean isSuccess, String msg) {
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
