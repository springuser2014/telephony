package telephony.ws.resource.complaint;

import telephony.core.service.dto.request.ProductComplaintEditRequest;
import telephony.core.service.dto.response.ComplaintEditResponse;

public interface ComplaintEditResource {

	String URL = "/complaint/edit";
	
	ComplaintEditResponse edit(ProductComplaintEditRequest request);
}
