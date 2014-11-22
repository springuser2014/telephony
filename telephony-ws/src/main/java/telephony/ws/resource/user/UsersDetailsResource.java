package telephony.ws.resource.user;

import telephony.core.service.dto.request.UserDetailsRequest;
import telephony.core.service.dto.response.UserDetailsResponse;

public interface UsersDetailsResource {

	String URL = "/users/details";
	
	UserDetailsResponse details(UserDetailsRequest request);
}
