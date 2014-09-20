package telephony.ws.resource.sale;

import telephony.core.service.dto.SalesDetailsRequestDto;
import telephony.core.service.dto.SalesDetailsResponseDto;

public interface SalesDetailsResource {

	String URL = "/sales/details";
	
	SalesDetailsResponseDto details(SalesDetailsRequestDto request);
}
