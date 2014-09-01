package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintDetailsRequest;
import telephony.core.service.dto.ComplaintDetailsResponse;

public interface ComplaintDetailsResource {

	String URL = "/complaint/details";
	
	ComplaintDetailsResponse details(ComplaintDetailsRequest request); 
	
}
