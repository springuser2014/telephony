package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ComplaintChangeStatusRequest extends AuthRequest {

    Long complaintId;

    public ComplaintChangeStatusRequest() {
        super();
    }

    public ComplaintChangeStatusRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }
}
