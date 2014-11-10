package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintCommentDto;
import telephony.core.service.dto.response.ComplaintAddCommentResponse;

public interface ComplaintAddCommentResource {

	String URL = "/complaint/addComment";
	
	ComplaintAddCommentResponse addComment(ComplaintCommentDto request);
	
}
