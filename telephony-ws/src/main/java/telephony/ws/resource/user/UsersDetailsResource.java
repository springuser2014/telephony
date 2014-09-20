package telephony.ws.resource.user;

import telephony.core.service.dto.UserDetailsRequestDto;
import telephony.core.service.dto.UserDetailsResponseDto;

public interface UsersDetailsResource {

	String URL = "/users/details";
	
	UserDetailsResponseDto details(UserDetailsRequestDto request);
}
