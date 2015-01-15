package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.UserDto;
import telephony.core.service.dto.UserEditDto;

public class UserEditRequest extends AuthRequest {

    UserEditDto userDto;

    public UserEditRequest() {
        super();
    }

    public UserEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public UserEditDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserEditDto userDto) {
        this.userDto = userDto;
    }
}
