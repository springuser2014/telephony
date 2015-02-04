package telephony.core.entity.jpa;

import javax.persistence.*;
import java.util.List;


@Entity
@DiscriminatorValue("P")
@Table(name = "complaints")
public class ProductComplaint extends Complaint {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = true)
    Product product;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "complaint")
    List<ProductComplaintComment> comments;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductComplaintComment> getComments() {
        return comments;
    }

    public void setComments(List<ProductComplaintComment> comments) {
        this.comments = comments;
    }
}
