
package telephony.ws.resource.session.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SessionManager;
import telephony.core.service.dto.request.SessionValidationRequest;
import telephony.core.service.dto.response.SessionValidationResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.session.SessionValidationResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SessionValidationResourceImpl
extends TelephonyServerResource
implements SessionValidationResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SessionManager sessionManager;

	@Override
	@Post("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SessionValidationResponse validate(SessionValidationRequest validationRequest) {

		logger.info("SessionValidationResourceImpl.validation starts");

		Boolean isValid = null;

		SessionValidationResponse resp = new SessionValidationResponse();

		try {
			isValid = new Boolean(sessionManager.validate(validationRequest.getSessionDto()));
		} catch (SessionServiceException ex) {
			logger.error("Error occured during session validation ", ex);
		}

		if (isValid == null) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
			resp.setMessage("internalError");
			resp.setSuccess(false);
			resp.setValid(null);

			return resp;
		} else {

			resp.setValid(isValid.booleanValue());
			resp.setMessage("operation performed successfully");
			resp.setSuccess(true);
			return resp;
		}
	}


}
