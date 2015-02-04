package telephony.ws.resource.user.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.UserService;
import telephony.core.service.dto.request.UserChangePasswordRequest;
import telephony.core.service.dto.response.UserChangePasswordResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersChangePasswordResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class UsersChangePasswordResourceImpl
extends TelephonyServerResource
implements UsersChangePasswordResource {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    UserService userService;

    @Override
    @Post("json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserChangePasswordResponse changePassword(UserChangePasswordRequest request) {

        logger.info("UsersChangePasswordResourceImpl.changePassword starts");

        UserChangePasswordResponse resp = new UserChangePasswordResponse();

        try {
            resp = userService.changePassword(request);
        } catch (SessionServiceException e) {
            logger.error("authorization occurred during changing user password ", e);

            resp.setMessage("sessionExpired");
            resp.setSuccess(false);
            getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

            return resp;
        } catch (Exception e) {
            logger.error("internal error occurred during changing user password", e);

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
