package telephony.core.service.dto.request;

import telephony.core.service.dto.DeliveryEditDto;
import telephony.core.service.dto.SessionDto;

public class DeliveryEditRequest extends AuthRequest {

	private DeliveryEditDto deliveryDto;

	public DeliveryEditRequest() {}

	public DeliveryEditRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public DeliveryEditDto getDeliveryDto() {
		return deliveryDto;
	}

	public void setDeliveryDto(DeliveryEditDto deliveryDto) {
		this.deliveryDto = deliveryDto;
	}

}
