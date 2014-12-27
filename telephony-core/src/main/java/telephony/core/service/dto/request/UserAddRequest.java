package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.UserAddDto;
import telephony.core.service.dto.UserDto;

public class UserAddRequest extends AuthRequest {

    UserAddDto userDto;

    public UserAddRequest() {
        super();
    }

    public UserAddRequest(SessionDto session) {
        super(session);
    }

    public UserAddDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserAddDto userDto) {
        this.userDto = userDto;
    }
}
