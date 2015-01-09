package telephony.core.service.dto.request;

import telephony.core.query.filter.ProductComplaintFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class ProductComplaintFetchRequest
        extends ComplaintFetchRequest<ProductComplaintFilterCriteria> {

    public ProductComplaintFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
