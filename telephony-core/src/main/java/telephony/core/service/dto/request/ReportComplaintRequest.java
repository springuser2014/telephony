package telephony.core.service.dto.request;

import telephony.core.service.dto.ComplaintDto;
import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.SessionDto;

public abstract class ReportComplaintRequest<T extends ComplaintDto> extends AuthRequest {

    T complaint;

    public ReportComplaintRequest() {
        super();
    }

    public ReportComplaintRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public T getComplaint() {
        return complaint;
    }

    public void setComplaint(T complaint) {
        this.complaint = complaint;
    }
}
