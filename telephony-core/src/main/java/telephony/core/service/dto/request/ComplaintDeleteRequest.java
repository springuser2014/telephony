package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ComplaintDeleteRequest extends AuthRequest {

    Long complaintId;

    public ComplaintDeleteRequest() {
        super();
    }

    public ComplaintDeleteRequest(SessionDto session) {
        super(session);
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }
}
