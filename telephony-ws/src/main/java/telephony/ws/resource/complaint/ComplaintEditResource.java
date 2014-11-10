package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ComplaintEditRequestDto;
import telephony.core.service.dto.response.ComplaintEditResponseDto;

public interface ComplaintEditResource {

	String URL = "/complaint/edit";
	
	ComplaintEditResponseDto edit(ComplaintEditRequestDto request);
}
