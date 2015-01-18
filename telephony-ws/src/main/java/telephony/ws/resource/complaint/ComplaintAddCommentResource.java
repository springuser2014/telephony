package telephony.ws.resource.complaint;

import org.restlet.resource.Post;
import telephony.core.service.dto.BaseComplaintCommentDto;
import telephony.core.service.dto.response.ComplaintAddCommentResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ComplaintAddCommentResource {

	String URL = "/complaint/comment";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ComplaintAddCommentResponse addComment(BaseComplaintCommentDto request);
	
}
