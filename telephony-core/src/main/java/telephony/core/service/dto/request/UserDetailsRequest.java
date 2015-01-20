package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class UserDetailsRequest extends AuthRequest {

    Long userId;

    public UserDetailsRequest() {
        super();
    }

    public UserDetailsRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
