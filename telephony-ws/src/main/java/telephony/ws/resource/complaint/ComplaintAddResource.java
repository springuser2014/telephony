package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.AddComplaintRequestDto;
import telephony.core.service.dto.response.ComplaintAddResponseDto;

public interface ComplaintAddResource {
	
	String URL = "/complaint/add";

	ComplaintAddResponseDto add(AddComplaintRequestDto request);

}
