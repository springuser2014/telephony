package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintFetchRequestDto;
import telephony.core.service.dto.ComplaintFetchResponseDto;

public interface ComplaintFetchResource {

	String URL = "/complaint/fetch";
	
	ComplaintFetchResponseDto fetch(ComplaintFetchRequestDto request);
}
