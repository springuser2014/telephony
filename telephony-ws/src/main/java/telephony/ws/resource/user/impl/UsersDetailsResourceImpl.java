package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.request.UserDetailsRequest;
import telephony.core.service.dto.response.UserDetailsResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersDetailsResource;

public class UsersDetailsResourceImpl 
extends TelephonyServerResource 
implements UsersDetailsResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserDetailsResponse details(UserDetailsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
