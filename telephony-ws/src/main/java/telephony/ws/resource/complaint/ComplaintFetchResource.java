package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ComplaintFetchRequest;
import telephony.core.service.dto.response.ComplaintFetchResponse;

public interface ComplaintFetchResource {

	String URL = "/complaint/fetch";
	
	ComplaintFetchResponse fetch(ComplaintFetchRequest request);
}
