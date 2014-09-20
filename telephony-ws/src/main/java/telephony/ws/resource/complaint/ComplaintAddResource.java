package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintAddRequestDto;
import telephony.core.service.dto.ComplaintAddResponseDto;

public interface ComplaintAddResource {
	
	String URL = "/complaint/add";

	ComplaintAddResponseDto add(ComplaintAddRequestDto request);

}
