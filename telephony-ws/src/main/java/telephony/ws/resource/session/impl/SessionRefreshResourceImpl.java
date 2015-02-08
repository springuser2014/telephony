package telephony.ws.resource.session.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SessionManager;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.SessionRefreshRequest;
import telephony.core.service.dto.response.SessionRefreshResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.session.SessionRefreshResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SessionRefreshResourceImpl
extends TelephonyServerResource
implements SessionRefreshResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SessionManager sessionManager;

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SessionRefreshResponse refresh(SessionRefreshRequest refreshRequest) {

		logger.info("SessionRefreshResource.refresh starts");

		SessionDto sessionToRefresh = refreshRequest.getSessionDto();

		SessionRefreshResponse resp = new SessionRefreshResponse();
		SessionDto session = null;

		try {
			session = sessionManager.refresh(sessionToRefresh);

		} catch (SessionServiceException e) {
			logger.error("Error occured during session refreshing.", e);
			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			return resp;
		}

		if (session == null) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);

			resp.setMessage("badRequest");
			resp.setSuccess(false);
			return resp;
		} else {
			getResponse().setStatus(Status.SUCCESS_OK);

			resp.setSuccess(true);
			resp.setMessage("operation performed successfully");
			resp.setSession(session);

			return resp;
		}
	}

}
