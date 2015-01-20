package telephony.ws.resource.role.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.RoleService;
import telephony.core.service.UserService;
import telephony.core.service.dto.request.RoleFetchRequest;
import telephony.core.service.dto.response.RoleFetchResponse;
import telephony.core.service.dto.response.UserAddResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.role.RoleFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class RoleFetchResourceImpl
extends TelephonyServerResource
implements RoleFetchResource {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    RoleService roleService;

    @Override
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RoleFetchResponse fetch(RoleFetchRequest fetchRequest) {

        logger.info("RoleFetchResourceImpl.add method");

        RoleFetchResponse resp = new RoleFetchResponse();

        try {
            resp = roleService.fetch(fetchRequest);
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
