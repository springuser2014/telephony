package telephony.core.entity.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("S")
@Table(name = "complaints")
public class SaleComplaint extends Complaint {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = true)
    Sale sale;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "complaint")
    List<SaleComplaintComment> comments;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale product) {
        this.sale = product;
    }

    public List<SaleComplaintComment> getComments() {
        return comments;
    }

    public void setComments(List<SaleComplaintComment> comments) {
        this.comments = comments;
    }
}
