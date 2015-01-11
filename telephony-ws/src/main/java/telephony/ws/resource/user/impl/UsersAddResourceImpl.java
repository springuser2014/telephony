package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.UserService;
import telephony.core.service.dto.request.UserAddRequest;
import telephony.core.service.dto.response.UserAddResponse;
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

        UserAddResponse resp = new UserAddResponse();

        return resp;
    }
}
