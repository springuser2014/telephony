package telephony.core.service.dto;

public class ComplaintCommentDto extends BaseComplaintCommentDto {

    Long complaintId;

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }
}
