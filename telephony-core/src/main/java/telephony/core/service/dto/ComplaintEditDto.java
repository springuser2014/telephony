package telephony.core.service.dto;

public abstract class ComplaintEditDto extends ComplaintDto {

    Long complaintId;

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }
}
