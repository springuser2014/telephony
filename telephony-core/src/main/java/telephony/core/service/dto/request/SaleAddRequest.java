package telephony.core.service.dto.request;

import telephony.core.service.dto.SaleAddDto;
import telephony.core.service.dto.SessionDto;


public class SaleAddRequest extends AuthRequest {

	SaleAddDto sale;

	public SaleAddRequest() {
		super();
	}

	public SaleAddRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public SaleAddDto getSale() {
		return sale;
	}

	public void setSaleDto(SaleAddDto sale) {
		this.sale = sale;
	}
}
