package telephony.ws.resource.sale;

import telephony.core.service.dto.request.SalesDetailsRequestDto;
import telephony.core.service.dto.response.SalesDetailsResponseDto;

public interface SalesDetailsResource {

	String URL = "/sales/details";
	
	SalesDetailsResponseDto details(SalesDetailsRequestDto request);
}
