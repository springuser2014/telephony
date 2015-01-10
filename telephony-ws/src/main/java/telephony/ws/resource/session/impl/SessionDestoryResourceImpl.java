package telephony.ws.resource.session.impl;

import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.SessionService;
import telephony.core.service.dto.request.SessionDestroyRequest;
import telephony.core.service.dto.response.SessionDestroyResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.session.SessionDestroyResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * asd.
 */
public class SessionDestoryResourceImpl extends TelephonyServerResource
		implements SessionDestroyResource {
	
	 private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SessionService sessionService;

	@Override	
    @Delete("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SessionDestroyResponse destroy(SessionDestroyRequest destroyRequest) {

        logger.info("SessionDestoryResourceImpl.destroy starts");

        SessionDestroyResponse resp = new SessionDestroyResponse();
        Boolean success = null;

        try {
            success = sessionService.destroy(destroyRequest.getSessionDto());
        } catch (SessionServiceException e) { 
        	logger.error("Error occured during session ending.", e);
        }

        if (success == null) {
            getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

            resp.setMessage("internalError");
            resp.setSuccess(false);
            resp.setDestroyed(false);

            return resp;
        } else {

            resp.setMessage("operation performed successfully");
            resp.setSuccess(true);
            resp.setDestroyed(success.booleanValue());

            return resp;
        }
    }
}
