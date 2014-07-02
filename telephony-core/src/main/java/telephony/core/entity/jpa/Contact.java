package telephony.core.entity.jpa;

import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * asd.
 */
@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {
	
    @OneToMany(
        mappedBy = "contact",
        fetch    = FetchType.LAZY,
        cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
    )
    private Collection<Delivery> deliveries;
    
    @Column(
        name     = "details",
        nullable = false,
        length   = 1000
    )
    private String details;
    
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
    private Long id;
    
    @Column(
        name     = "label",
        nullable = false,
        length   = 100
    )
    private String label;
    
    @OneToMany(
        mappedBy = "contact",
        fetch    = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
    )
    private Collection<Sale> sales;
   
    @CollectionTable(
    		name = "emails",
    		joinColumns = @JoinColumn(name = "contact_id")
    )
    @ElementCollection
    private Collection<Email> emails;
    
    
    @CollectionTable(
    		name = "phonenumbers",
    		joinColumns = @JoinColumn(name = "contact_id")
    )
    @ElementCollection
    private Collection<PhoneNumber> phonenumbers;
    
    /**
     * asd.
     * @param delivery asd.
     */
    public void addDelivery(Delivery delivery) {
    	
    	if (deliveries.contains(delivery)) {
			return;
		}
    	
    	deliveries.add(delivery);
    	delivery.setContact(this);
		
	}
    
    /**
     * asd.
     * @param delivery asd.
     */
    public void removeDelivery(Delivery delivery) {
		
    	if (!deliveries.contains(delivery)) {
			return;
		}
    	
    	deliveries.remove(delivery);
    	delivery.setContact(null);		
	}
    
    /**
     * asd.
     * @param sale asd.
     */
    public void addSale(Sale sale) {
		
    	if (sales.contains(sale)) {
			return;
		}
    	
    	sales.add(sale);
    	sale.setContact(this);
	}
    
    /**
     * asd.
     * @param sale asd.
     */
    public void removeSale(Sale sale) {
    	
    	if (!sales.contains(sale)) {
			return;
		}
    	
    	sales.remove(sale);
    	sale.setContact(null);
	}

    /**
     * Do nothing.
     */
    public Contact() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets contact's label.
     * @return contact's label.
     */
    public final String getLabel() {
        return label;
    }

    /**
     * Sets contact's label.
     * @param label the label.
     */
    public final void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets contact's details.
     * @return contact's details.
     */
    public final String getDetails() {
        return details;
    }

    /**
     * Sets contact's details.
     * @param details the details.
     */
    public final void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets contact's deliveries.
     * @return Collection of deliveries.
     */
    public final Collection<Delivery> getDeliveries() {
        return deliveries;
    }

    /**
     * Gets contact's sales.
     * @return asd.
     */
    public final Collection<Sale> getSales() {
        return sales;
    }

    /**
     * Sets contact's deliveries.
     * @param deliveries of contact.
     */
    public final void setDeliveries(Collection<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    /**
     * Sets contact's sales.
     * @param sales of contact.
     */
    public final void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }

	
}
