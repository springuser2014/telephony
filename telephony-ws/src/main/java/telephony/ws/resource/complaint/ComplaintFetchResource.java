package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ProductComplaintFetchRequest;
import telephony.core.service.dto.response.ComplaintFetchResponse;

public interface ComplaintFetchResource {

	String URL = "/complaint/fetch";
	
	ComplaintFetchResponse fetch(ProductComplaintFetchRequest request);
}
