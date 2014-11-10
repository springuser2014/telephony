package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ComplaintDeleteRequestDto;
import telephony.core.service.dto.response.ComplaintDeleteResponseDto;

public interface ComplaintDeleteResource {

	String URL = "/complaint/delete";
	
	ComplaintDeleteResponseDto delete(ComplaintDeleteRequestDto request);
}
