package telephony.ws.resource.user.impl;

import com.google.inject.Inject;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.UserService;
import telephony.core.service.dto.request.UsersFetchRequest;
import telephony.core.service.dto.response.UsersFetchResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class UsersFetchResourceImpl
extends TelephonyServerResource 
implements UsersFetchResource {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    UserService userService;

    @Override
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UsersFetchResponse fetch(UsersFetchRequest fetchRequest) {

        logger.info("UsersResource.get method");

        UsersFetchResponse resp = new UsersFetchResponse();



        return resp;
    }
}
