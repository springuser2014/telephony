package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.UserDto;

public class UserAddRequest extends AuthRequest {

    UserDto userDto;

    public UserAddRequest() {
        super();
    }

    public UserAddRequest(SessionDto session) {
        super(session);
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
