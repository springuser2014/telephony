package telephony.core.entity.jpa;

import javax.persistence.*;

/**
 * asd.
 */
@Entity
@DiscriminatorValue("S")
public class SaleComplaint extends Complaint {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id", nullable = true)
    Sale sale;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale product) {
        this.sale = product;
    }

}
