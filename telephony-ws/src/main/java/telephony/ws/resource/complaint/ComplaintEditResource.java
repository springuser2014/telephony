package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintEditRequest;
import telephony.core.service.dto.ComplaintEditResponse;

public interface ComplaintEditResource {

	String URL = "/complaint/edit";
	
	ComplaintEditResponse edit(ComplaintEditRequest request);
}
