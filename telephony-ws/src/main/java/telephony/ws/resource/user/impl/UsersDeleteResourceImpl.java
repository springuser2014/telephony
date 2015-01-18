package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.UserService;
import telephony.core.service.dto.request.UserDeleteRequest;
import telephony.core.service.dto.response.UserDeleteResponse;
import telephony.core.service.dto.response.UsersFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersDeleteResource;

public class UsersDeleteResourceImpl
extends TelephonyServerResource 
implements UsersDeleteResource {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	UserService userService;

	@Override
	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserDeleteResponse delete(UserDeleteRequest deleteRequest) {

		logger.info("UsersDeleteResourceImpl.delete method");

		UserDeleteResponse resp = new UserDeleteResponse();

		try {
			resp = userService.delete(deleteRequest);
		} catch (SessionServiceException e) {
			logger.error("authorization occurred during deleting user", e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			return resp;
		} catch (Exception e) {
			logger.error("internal error occurred during deleting user", e);

			resp.setMessage("internalError");
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
