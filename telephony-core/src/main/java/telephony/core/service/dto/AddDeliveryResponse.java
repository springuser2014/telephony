package telephony.core.service.dto;

import java.util.HashMap;

public class AddDeliveryResponse {
	
	private boolean success;
	
	private HashMap<String,String> errors;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}
	
	public void addMapElement(String key, String val) {
		
		if (!this.errors.containsKey(key)) {
			this.errors.put(key, val);
		}
	}
	
	public void removeMapElement(String key) {
		
		if (this.errors.containsKey(key)) {
			this.errors.remove(key);
		}
	}
}
