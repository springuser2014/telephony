package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class SaleComplaintDetailsFetchRequest extends ComplaintDetailsFetchRequest  {

    public SaleComplaintDetailsFetchRequest() {
        super();
    }

    public SaleComplaintDetailsFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

}
