package telephony.ws.resource.user.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.UserService;
import telephony.core.service.dto.request.UserEditRequest;
import telephony.core.service.dto.response.UserEditResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersEditResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class UsersEditResourceImpl
extends TelephonyServerResource
implements UsersEditResource {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	UserService userService;

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserEditResponse edit(UserEditRequest editRequest) {

		logger.info("UsersEditResourceImpl.edit method");

		UserEditResponse resp = new UserEditResponse();

		try {
			resp = userService.edit(editRequest);
		} catch (SessionServiceException e) {
			logger.error("authorization occurred during fetching user", e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			return resp;
		} catch (Exception e) {
			logger.error("internal error occurred during fetching user", e);

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
