package telephony.core.service.dto.response;

import java.util.HashSet;
import java.util.Set;

/**
 * asd.
 */
public class BasicResponse {

	private Set<Error> errors;
	private boolean success;
	private String message;
	
	/**
	 * asd.
	 */
	public BasicResponse() {
		this.errors = new HashSet<Error>();
	}
	
	/**
	 * asd.
	 * @param isSuccess asd.
	 * @param msg asd.
	 */
	public BasicResponse(boolean isSuccess, String msg) {
		this();
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

	/**
	 * asd.
	 * @return ads.
	 */
	public Set<Error> getErrors() {
		return errors;
	}

	/**
	 * d.
	 * @param errors d.
	 */
	public void setErrors(Set<Error> errors) {
		this.errors = errors;
	}

	/**
	 * asd.
	 * @param error a.
	 */
	public void addError(Error error) {

		this.errors.add(error);
	}

	/**
	 * asd.
	 * @param fieldId a.
	 * @param errorMsg a.
	 */
	public void addError(String fieldId, String errorMsg) {

		this.addError(new Error(fieldId, errorMsg));
	}

	/**
	 * asd.
	 * @param error d.
	 */
	public void removeError(Error error) {

		this.errors.add(error);
	}


}
