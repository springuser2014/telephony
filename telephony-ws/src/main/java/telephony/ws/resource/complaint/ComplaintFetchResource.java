package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ComplaintFetchRequestDto;
import telephony.core.service.dto.response.ComplaintFetchResponseDto;

public interface ComplaintFetchResource {

	String URL = "/complaint/fetch";
	
	ComplaintFetchResponseDto fetch(ComplaintFetchRequestDto request);
}
