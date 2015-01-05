package telephony.core.entity.jpa;

import javax.persistence.*;


/**
 * asd.
 */
@Entity
@DiscriminatorValue("P")
public class ProductComplaint extends Complaint {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = true)
    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
