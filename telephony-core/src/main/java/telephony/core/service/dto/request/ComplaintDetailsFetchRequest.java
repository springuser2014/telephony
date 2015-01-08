package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public abstract class ComplaintDetailsFetchRequest extends AuthRequest {

    Long complaintId;

    public ComplaintDetailsFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }
}
