package telephony.ws.resource.complaint;

import telephony.core.service.dto.BaseComplaintCommentDto;
import telephony.core.service.dto.response.ComplaintAddCommentResponse;

public interface ComplaintAddCommentResource {

	String URL = "/complaint/addComment";
	
	ComplaintAddCommentResponse addComment(BaseComplaintCommentDto request);
	
}
