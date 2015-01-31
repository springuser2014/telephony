package telephony.core.entity.jpa;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Id
    @Column(
    		name = "id", updatable = false, nullable = false
    )
    @GeneratedValue(
    		strategy = GenerationType.SEQUENCE, 
    		generator = "sales_seq"
    )
    @SequenceGenerator(
    		name = "sales_seq", 
    		sequenceName = "sales_seq", 
    		allocationSize = 1
    )
    private Long id;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "date_out")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOut;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
    private Set<Product> products;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    private Contact contact;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sale", cascade = CascadeType.ALL)
//    private Collection<SaleComplaint> complaints;

    public Sale() {
        products = new HashSet<>();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public void addProduct(Product product) {
		
		if (products.contains(product)) {
			return;
		}
		
		products.add(product);
		product.setSale(this);		
	}
	
	public void removeProduct(Product product) {
		
		if (!products.contains(product)) {
			return;
		}
		
		products.remove(product);
		product.setSale(null);
	}

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Store getStore() {
        return store;
    }

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

//    public void addComplaint(SaleComplaint sc) {
//        complaints.add(sc);
//    }
//
//    public void removeComplaint(SaleComplaint sc) {
//        complaints.remove(sc);
//    }
//
//    public Collection<SaleComplaint> getComplaints() {
//        return complaints;
//    }
//
//    public void setComplaints(Collection<SaleComplaint> complaints) {
//        this.complaints = complaints;
//    }

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