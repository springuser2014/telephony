package telephony.ws.resource.user;

import telephony.core.service.dto.request.UserDetailsRequest;
import telephony.core.service.dto.response.UserDetailsResponse;

@Deprecated
public interface UsersDetailsResource {

	String URL = "/users/details";
	
	UserDetailsResponse details(UserDetailsRequest request);
}
