package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ProductComplaintDetailsFetchRequest extends ComplaintDetailsFetchRequest {

    public ProductComplaintDetailsFetchRequest() {
        super();
    }

    public ProductComplaintDetailsFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
