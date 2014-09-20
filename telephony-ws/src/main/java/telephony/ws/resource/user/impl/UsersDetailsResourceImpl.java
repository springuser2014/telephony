package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.UserDetailsRequestDto;
import telephony.core.service.dto.UserDetailsResponseDto;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersDetailsResource;

public class UsersDetailsResourceImpl 
extends TelephonyServerResource 
implements UsersDetailsResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserDetailsResponseDto details(UserDetailsRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

}
