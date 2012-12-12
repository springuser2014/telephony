package telephony.core.entity;

import telephony.core.entity.common.BaseEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contacts_seq")
    @SequenceGenerator(name = "contacts_seq", sequenceName = "contacts_seq", allocationSize = 1)
    private Long id;

    @Column(name = "label", nullable = false, length = 100)
    private String label;

    @Column(name = "details", nullable = false, length = 1000)
    private String details;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    private Collection<Delivery> deliveries;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    private Collection<Sale> sales;

    public Contact() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Collection<Delivery> getDeliveries() {
        return deliveries;
    }

    public Collection<Sale> getSales() {
        return sales;
    }

    public void setDeliveries(Collection<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }
}
