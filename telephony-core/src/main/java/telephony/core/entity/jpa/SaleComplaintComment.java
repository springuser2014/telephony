package telephony.core.entity.jpa;

import javax.persistence.*;

@Entity
@Table(name = "complaint_comments")
public class SaleComplaintComment extends ComplaintComment {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "complaint_id", nullable = false)
    SaleComplaint complaint;

    public SaleComplaint getComplaint() {
        return complaint;
    }

    public void setComplaint(SaleComplaint complaint) {
        this.complaint = complaint;
    }
}
