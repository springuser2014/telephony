package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.AddComplaintRequest;
import telephony.core.service.dto.response.ComplaintAddResponse;

public interface ComplaintAddResource {
	
	String URL = "/complaint/add";

	ComplaintAddResponse add(AddComplaintRequest request);

}
