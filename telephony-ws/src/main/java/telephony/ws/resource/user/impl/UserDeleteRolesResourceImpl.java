package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.UserService;
import telephony.core.service.dto.request.UserDeleteRequest;
import telephony.core.service.dto.response.UserDeleteResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersDeleteResource;

import com.google.inject.Inject;

@Deprecated
public class UserDeleteRolesResourceImpl
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
		// TODO Auto-generated method stub
		return null;
	}

}
