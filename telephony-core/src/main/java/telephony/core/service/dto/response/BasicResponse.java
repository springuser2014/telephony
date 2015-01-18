package telephony.core.service.dto.response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static telephony.core.assertion.CommonAssertions.*;

/**
 * asd.
 */
public class BasicResponse {

	private List<Error> errors;
	private boolean success;
	private String message;
	
	/**
	 * asd.
	 */
	public BasicResponse() {
		this.errors = new ArrayList<Error>();
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
	public List<Error> getErrors() {

		return errors;
	}

	/**
	 * d.
	 * @param errors d.
	 */
	public void setErrors(List<Error> errors) {
		if (isNotNull(errors)) {
			this.errors = errors;
		}
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

	/**
	 * asd.
	 * @return a.
	 */
	public boolean hasErrors() {

		return !errors.isEmpty();
	}
}
