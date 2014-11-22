package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ComplaintDeleteRequest;
import telephony.core.service.dto.response.ComplaintDeleteResponse;

public interface ComplaintDeleteResource {

	String URL = "/complaint/delete";
	
	ComplaintDeleteResponse delete(ComplaintDeleteRequest request);
}
