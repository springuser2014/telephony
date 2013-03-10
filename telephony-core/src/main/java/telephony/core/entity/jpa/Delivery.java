package telephony.core.entity.jpa;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@Entity
@Table(name = "deliveries")
public class Delivery extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "deliveries_seq")
    @SequenceGenerator(
        name = "deliveries_seq",
        sequenceName = "deliveries_seq",
        allocationSize = 1)
    private Long id;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "date_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIn;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Collection<Product> products;

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
    public final Collection<Product> getProducts() {
        return products;
    }

    /**
     * asd.
     * @param products asd.
     */
    public final void setProducts(Collection<Product> products) {
        this.products = products;
    }

    /**
     * asd.
     */
    public Delivery() {
        products = new ArrayList<Product>();
    }

    /**
     * asd.
     * @return asd.
     */
    public final Date getDateIn() {
        return dateIn;
    }

    /**
     * asd.
     * @param dateIn asd.
     */
    public final void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    /**
     * asd.
     * @return asd.
     */
    public final Store getStore() {
        return store;
    }

    /**
     * asd.
     * @param store asd.
     */
    public final void setStore(Store store) {
        this.store = store;
    }

    /**
     * asd.
     * @param o asd.
     * @return asd.
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Delivery delivery = (Delivery) o;

        if (dateIn != null
            ? !dateIn.equals(delivery.dateIn) : delivery.dateIn != null) {
            return false;
        }
        if (label != null
            ? !label.equals(delivery.label) : delivery.label != null) {
            return false;
        }
        if (products != null
            ? !products.equals(delivery.products) : delivery.products != null) {
            return false;
        }
        if (store != null
            ? !store.equals(delivery.store) : delivery.store != null) {
            return false;
        }
        if (contact != null
            ? !contact.equals(delivery.contact) : delivery.contact != null) {
            return false;
        }

        return true;
    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (dateIn != null ? dateIn.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        return result;
    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final Long getId() {
        return this.id;
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
    public final Contact getContact() {
        return contact;
    }

    /**
     * asd.
     * @param contact asd.
     */
    public final void setContact(Contact contact) {
        this.contact = contact;
    }


}
