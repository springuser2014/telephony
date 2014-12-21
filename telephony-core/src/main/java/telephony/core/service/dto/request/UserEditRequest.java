package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.UserDto;

public class UserEditRequest extends AuthRequest {

    UserDto userDto;

    public UserEditRequest() {
        super();
    }

    public UserEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
