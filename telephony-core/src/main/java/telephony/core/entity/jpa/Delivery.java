package telephony.core.entity.jpa;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
   
    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Product> products = new HashSet<Product>();

    public final String getLabel() {
        return label;
    }

    public final void setLabel(String label) {
        this.label = label;
    }

    public void addProduct(Product product) {
    	
    	if (products.contains(product)) {
			return;
		}
    	
    	products.add(product);
    	product.setDelivery(this);
    }
    
    public void removeProduct(Product product) {
    	
    	if (!products.contains(product)) {
			return;
		}
    	
    	products.remove(product);
    	product.setDelivery(null);
    }

    public final Collection<Product> getProducts() {
        return products;
    }

    public final void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Delivery() {
        products = new HashSet<Product>();
    }

    public final Date getDateIn() {
        return dateIn;
    }

    public final void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public final Store getStore() {
        return store;
    }

    public final void setStore(Store store) {
    	
    	if (sameAsFormer(store)) {
			return;
		}
    	
        Store oldStore = this.store;
        this.store = store;
        
        if (oldStore != null) {
        	oldStore.removeDelivery(this);
        }
        	
        if (store != null) {
        	store.addDelivery(this);
        }
    }
    
    private boolean sameAsFormer(Store store) {
    	return this.store == null ?
    				store == null :
    					this.store.equals(store);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        Delivery delivery = (Delivery) obj;

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

    @Override
    public final int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (dateIn != null ? dateIn.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        return result;
    }

    @Override
    public final Long getId() {
        return this.id;
    }

    @Override
    public final void setId(Long id) {
        this.id = id;
    }

    public final Contact getContact() {
        return contact;
    }

    public final void setContact(Contact contact) {
    	
    	if (sameAsFormer(contact)) {
			return;
		}
    	
    	Contact oldContact = this.contact;
    	
    	if (oldContact != null) {
			oldContact.removeDelivery(this);
		}
    	
    	this.contact = contact;
    }

	private boolean sameAsFormer(Contact contact) {
		return this.contact == null 
				? contact == null 
				: this.contact.equals(contact);
	}
}
