package telephony.ws.resource.user;

import telephony.core.service.dto.request.UserDetailsRequestDto;
import telephony.core.service.dto.response.UserDetailsResponseDto;

public interface UsersDetailsResource {

	String URL = "/users/details";
	
	UserDetailsResponseDto details(UserDetailsRequestDto request);
}
