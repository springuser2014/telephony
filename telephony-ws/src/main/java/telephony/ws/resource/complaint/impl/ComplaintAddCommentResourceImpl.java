package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ComplaintCommentService;
import telephony.core.service.dto.BaseComplaintCommentDto;
import telephony.core.service.dto.request.ComplaintCommentRequest;
import telephony.core.service.dto.response.ComplaintAddCommentResponse;
import telephony.core.service.dto.response.ComplaintCommentResponse;
import telephony.core.service.dto.response.ContactAddResponse;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.impl.ComplaintCommentServiceImpl;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintAddCommentResource;

public class ComplaintAddCommentResourceImpl
extends TelephonyServerResource
implements ComplaintAddCommentResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ComplaintCommentService commentService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintCommentResponse addComment(ComplaintCommentRequest request) {

		logger.info("ComplaintAddCommentResourceImpl.addComment starts");

		ComplaintCommentResponse resp = new ComplaintCommentResponse();

		try {

			resp = commentService.comment(request);
		} catch (SessionServiceException e) {
			logger.info("sessionExpired", e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);

			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			return resp;
		} catch (Exception e) {
			logger.info("error occurred", e);

			resp.setMessage("error occurred");
			resp.setSuccess(false);

			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			return resp;
		}

		if (resp.hasErrors()) {

			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return resp;
		} else {

			getResponse().setStatus(Status.SUCCESS_OK);
			return resp;
		}
	}
	
}
