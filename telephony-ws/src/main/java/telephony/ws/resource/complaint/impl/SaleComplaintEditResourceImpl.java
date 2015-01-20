package telephony.ws.resource.complaint.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.dto.request.SaleComplaintEditRequest;
import telephony.core.service.dto.response.SaleComplaintEditResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.SaleComplaintEditResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SaleComplaintEditResourceImpl
extends TelephonyServerResource
implements SaleComplaintEditResource {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    SaleComplaintService saleComplaintService;

    @Put("json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public SaleComplaintEditResponse edit(SaleComplaintEditRequest request) {

        logger.info("SaleComplaintEditResourceImpl.edit starts");

        SaleComplaintEditResponse resp = new SaleComplaintEditResponse();

        try {
            resp = saleComplaintService.editComplaint(request);
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
