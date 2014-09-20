package telephony.ws.resource.complaint;

import telephony.core.service.dto.ComplaintAddCommentDto;
import telephony.core.service.dto.ComplaintAddCommentResponse;

public interface ComplaintAddCommentResource {

	String URL = "/complaint/addComment";
	
	ComplaintAddCommentResponse addComment(ComplaintAddCommentDto request);
	
}
