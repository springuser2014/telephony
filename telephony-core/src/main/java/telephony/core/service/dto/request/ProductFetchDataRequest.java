package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ProductFetchDataRequest extends AuthRequest {

    public ProductFetchDataRequest() {
        super();
    }

    public ProductFetchDataRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
