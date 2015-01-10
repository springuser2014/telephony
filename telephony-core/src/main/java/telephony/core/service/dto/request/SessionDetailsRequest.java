package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class SessionDetailsRequest extends AuthRequest {

    public SessionDetailsRequest() {
        super();
    }

    public SessionDetailsRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
