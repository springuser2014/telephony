package telephony.ws.resource.complaint.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ComplaintCommentService;
import telephony.core.service.dto.request.AnonymousComplaintCommentRequest;
import telephony.core.service.dto.response.AnonymousComplaintCommentResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintAddAnonymousCommentResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ComplaintAddAnonymousCommentResourceImpl
extends TelephonyServerResource
implements ComplaintAddAnonymousCommentResource {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    ComplaintCommentService commentService;

    @Override
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AnonymousComplaintCommentResponse addComment(AnonymousComplaintCommentRequest request) {

        logger.info("ComplaintAddAnonymousCommentResourceImpl.addComment starts");

        AnonymousComplaintCommentResponse resp = new AnonymousComplaintCommentResponse();

        try {

            resp = commentService.comment(request);
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
