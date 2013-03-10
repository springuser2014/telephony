package telephony.core.entity.jpa;



import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {
    @OneToMany(
        mappedBy = "contact",
        fetch    = FetchType.LAZY
    )
    private Collection<Delivery> deliveries;
    @Column(
        name     = "details",
        nullable = false,
        length   = 1000
    )
    private String               details;
    @Id
    @Column(
        name           = "id",
        updatable      = false,
        nullable       = false
    )
    @GeneratedValue(
        strategy       = GenerationType.SEQUENCE,
        generator      = "contacts_seq"
    )
    @SequenceGenerator(
        name           = "contacts_seq",
        sequenceName   = "contacts_seq",
        allocationSize = 1
    )
    private Long                 id;
    @Column(
        name     = "label",
        nullable = false,
        length   = 100
    )
    private String               label;
    @OneToMany(
        mappedBy = "contact",
        fetch    = FetchType.LAZY
    )
    private Collection<Sale>     sales;

    /**
     * asd.
     */
    public Contact() {

    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final Long getId() {
        return id;
    }

    /**
     * asd.
     * @param id asd.
     */
    @Override
    public final void setId(Long id) {
        this.id = id;
    }

    /**
     * asd.
     * @return asd.
     */
    public final String getLabel() {
        return label;
    }

    /**
     * asd.
     * @param label asd.
     */
    public final void setLabel(String label) {
        this.label = label;
    }

    /**
     * asd.
     * @return asd.
     */
    public final String getDetails() {
        return details;
    }

    /**
     * asd.
     * @param details asd.
     */
    public final void setDetails(String details) {
        this.details = details;
    }

    /**
     * asd.
     * @return asd.
     */
    public final Collection<Delivery> getDeliveries() {
        return deliveries;
    }

    /**
     * asd.
     * @return asd.
     */
    public final Collection<Sale> getSales() {
        return sales;
    }

    /**
     * asd.
     * @param deliveries asd.
     */
    public final void setDeliveries(Collection<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    /**
     * asd.
     * @param sales asd.
     */
    public final void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }
}
