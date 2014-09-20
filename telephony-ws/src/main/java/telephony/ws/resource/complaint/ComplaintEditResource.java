package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintEditRequestDto;
import telephony.core.service.dto.ComplaintEditResponseDto;

public interface ComplaintEditResource {

	String URL = "/complaint/edit";
	
	ComplaintEditResponseDto edit(ComplaintEditRequestDto request);
}
