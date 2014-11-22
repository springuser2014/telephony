package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ComplaintDetailsRequest;
import telephony.core.service.dto.response.ComplaintDetailsResponse;

public interface ComplaintDetailsResource {

	String URL = "/complaint/details";
	
	ComplaintDetailsResponse details(ComplaintDetailsRequest request);
	
}
