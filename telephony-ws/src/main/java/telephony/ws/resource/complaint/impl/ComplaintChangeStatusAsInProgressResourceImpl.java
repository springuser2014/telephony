package telephony.ws.resource.complaint.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.dto.request.ComplaintChangeStatusRequest;
import telephony.core.service.dto.response.ComplaintChangeStatusResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintChangeStatusAsInProgressResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ComplaintChangeStatusAsInProgressResourceImpl
extends TelephonyServerResource
implements ComplaintChangeStatusAsInProgressResource {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    ProductComplaintService productComplaintService;

    @Inject
    SaleComplaintService saleComplaintService;


    @Override
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ComplaintChangeStatusResponse markAsInProgress(ComplaintChangeStatusRequest request) {

        logger.info("ComplaintChangeStatusAsInProgressResourceImpl.markAsInProgress starts");

        ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();

        try {

            resp = productComplaintService.markAsAccepted(request);
            resp = saleComplaintService.markAsAccepted(request);

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
