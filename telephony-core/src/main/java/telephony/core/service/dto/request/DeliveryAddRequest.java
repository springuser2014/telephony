package telephony.core.service.dto.request;

import telephony.core.service.dto.DeliveryAddDto;
import telephony.core.service.dto.SessionDto;

public class DeliveryAddRequest extends AuthRequest {

	private DeliveryAddDto deliveryDto;

	public DeliveryAddRequest() {
		super();
	}

	public DeliveryAddRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public DeliveryAddDto getDeliveryDto() {
		return deliveryDto;
	}

	public void setDeliveryDto(DeliveryAddDto deliveryDto) {
		this.deliveryDto = deliveryDto;
	}
}
