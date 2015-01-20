package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class DashboardDetailsRequest extends AuthRequest {

    public DashboardDetailsRequest() {
        super();
    }

    public DashboardDetailsRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
