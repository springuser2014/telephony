package telephony.core.service.dto.request;

import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.SessionDto;

public class ReportComplaintRequest extends AuthRequest {

    ProductComplaintDto complaint;

    public ReportComplaintRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public ProductComplaintDto getComplaint() {
        return complaint;
    }

    public void setComplaint(ProductComplaintDto complaint) {
        this.complaint = complaint;
    }
}
