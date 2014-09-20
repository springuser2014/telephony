package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintDetailsRequestDto;
import telephony.core.service.dto.ComplaintDetailsResponseDto;

public interface ComplaintDetailsResource {

	String URL = "/complaint/details";
	
	ComplaintDetailsResponseDto details(ComplaintDetailsRequestDto request); 
	
}
