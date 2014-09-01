package telephony.ws.resource.user;

import telephony.core.service.dto.UserDetailsRequest;
import telephony.core.service.dto.UserDetailsResponse;

public interface UsersDetailsResource {

	String URL = "/users/details";
	
	UserDetailsResponse details(UserDetailsRequest request);
}
