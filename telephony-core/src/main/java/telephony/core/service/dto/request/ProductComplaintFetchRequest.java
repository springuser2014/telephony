package telephony.core.service.dto.request;

import telephony.core.query.filter.ComplaintFilterCriteria;
import telephony.core.query.filter.ProductComplaintFilterCriteria;
import telephony.core.service.dto.SessionDto;

import javax.mail.Session;

public class ProductComplaintFetchRequest
        extends ComplaintFetchRequest<ProductComplaintFilterCriteria> {

    public ProductComplaintFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
