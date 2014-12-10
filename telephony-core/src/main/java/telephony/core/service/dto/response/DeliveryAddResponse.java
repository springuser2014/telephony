package telephony.core.service.dto.response;

import java.util.HashMap;

public class DeliveryAddResponse extends BasicResponse {
	
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
