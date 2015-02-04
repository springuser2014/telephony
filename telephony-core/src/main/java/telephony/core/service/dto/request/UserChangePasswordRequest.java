package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.UserChangePasswordDto;

public class UserChangePasswordRequest extends AuthRequest {

    UserChangePasswordDto userDto;

    public UserChangePasswordRequest() {
        super();
    }

    public UserChangePasswordRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public UserChangePasswordDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserChangePasswordDto userDto) {
        this.userDto = userDto;
    }
}
