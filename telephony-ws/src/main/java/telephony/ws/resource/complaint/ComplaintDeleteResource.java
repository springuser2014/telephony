package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintDeleteRequestDto;
import telephony.core.service.dto.ComplaintDeleteResponseDto;

public interface ComplaintDeleteResource {

	String URL = "/complaint/delete";
	
	ComplaintDeleteResponseDto delete(ComplaintDeleteRequestDto request);
}
