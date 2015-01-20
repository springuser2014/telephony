package telephony.ws.resource.complaint.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.dto.request.SaleComplaintFetchRequest;
import telephony.core.service.dto.response.SaleComplaintFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.SaleComplaintFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SaleComplaintFetchResourceImpl
extends TelephonyServerResource
implements SaleComplaintFetchResource {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    SaleComplaintService saleComplaintService;

    @Override
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SaleComplaintFetchResponse fetch(SaleComplaintFetchRequest request) {

        logger.info("SaleComplaintFetchResourceImpl.edit starts");

        SaleComplaintFetchResponse resp = new SaleComplaintFetchResponse();

        try {
            resp = saleComplaintService.fetch(request);
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