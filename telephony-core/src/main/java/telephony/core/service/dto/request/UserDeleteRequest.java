package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class UserDeleteRequest extends AuthRequest {

    Long userId;

    public UserDeleteRequest() {
        super();
    }

    public UserDeleteRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
