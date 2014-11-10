package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ComplaintDetailsRequestDto;
import telephony.core.service.dto.response.ComplaintDetailsResponseDto;

public interface ComplaintDetailsResource {

	String URL = "/complaint/details";
	
	ComplaintDetailsResponseDto details(ComplaintDetailsRequestDto request); 
	
}
