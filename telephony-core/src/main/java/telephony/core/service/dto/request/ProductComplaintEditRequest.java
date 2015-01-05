package telephony.core.service.dto.request;

import telephony.core.service.dto.ProductComplaintEditDto;
import telephony.core.service.dto.SessionDto;

public class ProductComplaintEditRequest extends AuthRequest {

    ProductComplaintEditDto complaintEditDto;

    public ProductComplaintEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public ProductComplaintEditDto getComplaintEditDto() {
        return complaintEditDto;
    }

    public void setComplaintEditDto(ProductComplaintEditDto complaintEditDto) {
        this.complaintEditDto = complaintEditDto;
    }
}
