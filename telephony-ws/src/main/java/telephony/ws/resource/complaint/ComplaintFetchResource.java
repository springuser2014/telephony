package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintFetchRequest;
import telephony.core.service.dto.ComplaintFetchResponse;

public interface ComplaintFetchResource {

	String URL = "/complaint/fetch";
	
	ComplaintFetchResponse fetch(ComplaintFetchRequest request);
}
