package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintDeleteRequest;
import telephony.core.service.dto.ComplaintDeleteResponse;

public interface ComplaintDeleteResource {

	String URL = "/complaint/delete";
	
	ComplaintDeleteResponse delete(ComplaintDeleteRequest request);
}
