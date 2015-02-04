package telephony.core.entity.jpa;

import javax.persistence.*;

@Entity
@Table(name = "complaint_comments")
public class ProductComplaintComment extends ComplaintComment {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    ProductComplaint complaint;

    public ProductComplaint getComplaint() {
        return complaint;
    }

    public void setComplaint(ProductComplaint complaint) {
        this.complaint = complaint;
    }
}
