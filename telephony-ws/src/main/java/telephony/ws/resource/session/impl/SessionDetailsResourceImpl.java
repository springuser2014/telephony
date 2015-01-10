package telephony.ws.resource.session.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SessionService;
import telephony.core.service.dto.request.SessionDetailsRequest;
import telephony.core.service.dto.response.SessionDetailsResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.session.SessionDetailsResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SessionDetailsResourceImpl
extends TelephonyServerResource
implements SessionDetailsResource {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    SessionService sessionService;

    @Override
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SessionDetailsResponse details(SessionDetailsRequest request) {

        logger.info("SessionDetailsResourceImpl.details starts");

        SessionDetailsResponse resp = resp = new SessionDetailsResponse();

        try {
            resp = sessionService.fetchDetails(request);
        } catch (SessionServiceException ex) {
            logger.error("",ex);
            resp.setMessage("sessionExpired");
            resp.setSuccess(false);

            getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
            return resp;
        } catch (Exception ex) {
            logger.error("",ex);
            resp.setMessage("intrnalSeverError");
            resp.setSuccess(false);

            getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
            return resp;
        }

        getResponse().setStatus(Status.SUCCESS_OK);
        return resp;
    }
}
