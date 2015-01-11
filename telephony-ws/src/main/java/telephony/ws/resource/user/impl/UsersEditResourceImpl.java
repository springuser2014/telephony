package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.UserService;
import telephony.core.service.dto.request.UserEditRequest;
import telephony.core.service.dto.response.UserEditResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersEditResource;

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

		UserEditResponse resp = new UserEditResponse();

		return resp;
	}

}
