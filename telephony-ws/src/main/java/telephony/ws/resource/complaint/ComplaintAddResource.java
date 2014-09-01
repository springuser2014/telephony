package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintAddRequest;
import telephony.core.service.dto.ComplaintAddResponse;

public interface ComplaintAddResource {
	
	String URL = "/complaint/add";

	ComplaintAddResponse add(ComplaintAddRequest request);

}
