package telephony.core.service.dto.response;

public class ComplaintChangeStatusResponse extends BasicResponse {

    Long complaintId;

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }
}
