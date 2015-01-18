package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.UserService;
import telephony.core.service.dto.request.UserAddRequest;
import telephony.core.service.dto.response.UserAddResponse;
import telephony.core.service.dto.response.UserDeleteResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersAddResource;

public class UsersAddResourceImpl
extends TelephonyServerResource 
implements UsersAddResource {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    UserService userService;

    @Override
	@Post("json")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public final UserAddResponse add(UserAddRequest addRequest) {

        logger.info("UsersAddResourceImpl.add method");

        UserAddResponse resp = new UserAddResponse();

        try {
            resp = userService.add(addRequest);
        } catch (SessionServiceException e) {
            logger.error("authorization occurred during adding user", e);

            resp.setMessage("sessionExpired");
            resp.setSuccess(false);
            getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

            return resp;
        } catch (Exception e) {
            logger.error("internal error occurred during adding user", e);

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
