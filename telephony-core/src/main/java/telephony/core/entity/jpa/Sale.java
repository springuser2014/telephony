package telephony.core.entity.jpa;



import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_seq")
    @SequenceGenerator(name = "sales_seq", sequenceName = "sales_seq", allocationSize = 1)
    private Long id;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "date_out")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOut;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY)
    private Set<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    /**
     * asd.
     */
    public Sale() {

    }

    /**
     * asd.
     * @return asd.
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * asd.
     * @param contact asd.
     */
    public void setContact(Contact contact) {
        this.contact = contact;
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
    
	public void addProduct(Product product) {
		
		if (products.contains(product))
			return;
		
		products.add(product);
		product.setSale(this);		
	}
	
	public void removeProduct(Product product) {
		
		if (!products.contains(product))
			return;
		
		products.remove(product);
		product.setSale(null);
	}

    /**
     * asd.
     * @return asd.
     */
    public Set<Product> getProducts() {
        return products;
    }

    /**
     * asd.
     * @param products asd.
     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    /**
     * asd.
     * @return asd.
     */
    public Date getDateOut() {
        return dateOut;
    }

    /**
     * asd.
     * @param dateOut asd.
     */
    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    /**
     * asd.
     * @return asd.
     */
    public Store getStore() {
        return store;
    }

    /**
     * asd.
     * @param store asd.
     */
    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

        Sale sale = (Sale) o;

        if (dateOut != null ? !dateOut.equals(sale.dateOut) : sale.dateOut != null) {
            return false;
        }
        if (label != null ? !label.equals(sale.label) : sale.label != null) {
            return false;
        }
        if (products != null ? !products.equals(sale.products) : sale.products != null) {
            return false;
        }
        if (store != null ? !store.equals(sale.store) : sale.store != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + label.hashCode();
        result = 31 * result + dateOut.hashCode();
        result = 31 * result + store.hashCode();
        result = 31 * result + products.hashCode();
        return result;
    }


}