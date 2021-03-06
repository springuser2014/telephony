package telephony.core.entity.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "stores")
public class Store extends BaseEntity {

    @Id
    @Column(
		name = "id", 
		updatable = false, 
		nullable = false)
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "stores_seq")
    @SequenceGenerator(
		name = "stores_seq", 
		sequenceName = "stores_seq", 
		allocationSize = 1)
    private Long id;

    @Column(name = "label", nullable = false, length = 255)
    private String label;
    
//    @ManyToMany(mappedBy = "allowedShops")
//    private Set<User> users = new HashSet<User>();
      
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private Set<Product> products = new HashSet<Product>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private Set<Delivery> deliveries = new HashSet<Delivery>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private Set<Sale> sales = new HashSet<>();
    /**
     * sd.
     * @param delivery a.
     */
    public void addDelivery(Delivery delivery) {
    	
    	if (deliveries.contains(delivery)) {
    		return;
    	}
    	
    	deliveries.add(delivery);
    	delivery.setStore(this);
    }
    
    /**
     * asd.
     * @param delivery a.
     */
    public void removeDelivery(Delivery delivery) {
    	
    	if (!deliveries.contains(delivery)) {
    		return;
    	}
    	
    	deliveries.remove(delivery);
    	delivery.setStore(null);
    	
    }
    
    public Set<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Set<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    public void removeSale(Sale sale) {
        sales.remove(sale);
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    /**
	 * a.
	 * @param product a.
	 */
	public void addProduct(Product product) {
    	
    	if (products.contains(product)) {
    		return;
    	}
    	
    	this.products.add(product);
    	product.setStore(this);
    }
    
	/**
	 * asd.
	 * @param product a.
	 */
    public void removeProduct(Product product) {
    	
    	if (!products.contains(product)) { 
    		return;
    	}
    	
    	products.remove(product);
    	product.setStore(null);
    }
    
    public Set<Product> getProducts() {
		return products;
	}

    public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", label=" + label
				+ ", products=" + products
				+ ", deliveries=" + deliveries + "]";
	}

	/**
     * asd.
     * @return as.
     */
//    public Set<User> getUsers() {
//		return users;
//	}

    /**
     * asd.
     * @param users asd.
     */
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}

	/**
     * asd.
     */
    public Store() {

    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * asd.
     * @return asd.
     */
    public String getLabel() {
        return label;
    }

    /**
     * asd.
     * @param label asd.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Store store = (Store) o;

        if (label != null ? !label.equals(store.label) : store.label != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

}
