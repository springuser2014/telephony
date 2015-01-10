package telephony.core.service.dto.response;

import telephony.core.service.dto.SessionDto;

public class SessionInitializationResponse extends BasicResponse {

    SessionDto session;

    public SessionDto getSession() {
        return session;
    }

    public void setSession(SessionDto session) {
        this.session = session;
    }
}
