package telephony.core.service.dto.request;

import telephony.core.service.dto.ProductComplaintEditDto;
import telephony.core.service.dto.SessionDto;

public class ProductComplaintEditRequest extends ComplaintEditRequest<ProductComplaintEditDto> {

    public ProductComplaintEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

}
