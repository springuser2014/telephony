package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ComplaintEditRequest;
import telephony.core.service.dto.response.ComplaintEditResponse;

public interface ComplaintEditResource {

	String URL = "/complaint/edit";
	
	ComplaintEditResponse edit(ComplaintEditRequest request);
}
