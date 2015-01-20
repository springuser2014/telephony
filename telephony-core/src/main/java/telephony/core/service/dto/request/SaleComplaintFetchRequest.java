package telephony.core.service.dto.request;

import telephony.core.query.filter.SaleComplaintFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class SaleComplaintFetchRequest
extends ComplaintFetchRequest<SaleComplaintFilterCriteria> {

    public SaleComplaintFetchRequest() {
        super();
    }

    public SaleComplaintFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
