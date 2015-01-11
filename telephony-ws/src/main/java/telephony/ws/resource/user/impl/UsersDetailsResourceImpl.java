package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.UserService;
import telephony.core.service.dto.request.UserDetailsRequest;
import telephony.core.service.dto.response.UserDetailsResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersDetailsResource;

@Deprecated
public class UsersDetailsResourceImpl 
extends TelephonyServerResource 
implements UsersDetailsResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	UserService userService;

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserDetailsResponse details(UserDetailsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
