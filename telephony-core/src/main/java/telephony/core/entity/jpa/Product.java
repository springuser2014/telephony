package telephony.core.entity.jpa;

import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * asd.
 */
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "products_seq")
    @SequenceGenerator(
        name = "products_seq",
        sequenceName = "products_seq",
        allocationSize = 1)
    private Long id;

    @Column(name = "imei", nullable = false, length = 100)
    private String imei;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;
    
    @Column(name = "price_in", precision = 2)
    private Double priceIn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id", nullable = true)
    private Sale sale;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "color", length = 20, nullable = false)
    private String color;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_taxes",
		joinColumns = { 
			@JoinColumn(name = "product_id", referencedColumnName = "id") 
		},
		inverseJoinColumns = { 
			@JoinColumn(name = "tax_id", referencedColumnName = "id")
		}
    )		
    private Collection<Tax> taxes;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")    
    private Collection<Pricing> pricings;
    
    /* TODO : implement later
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "product_files",
		joinColumns = @JoinColumn(
			name = "product_id",
			referencedColumnName = "id"
		),
		inverseJoinColumns = @JoinColumn(
			name = "file_id",
			referencedColumnName = "id"))
    private Collection<File> files;
    */
    
    /**
     * asd.
     */
    public Product() {
    }

    /**
     * asd.
     * @return asd.
     */
    public String getImei() {
        return imei;
    }

    /**
     * asd.
     * @param imei asd.
     */
    public void setImei(String imei) {
        this.imei = imei;
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
     * @param pmStore asd.
     */
    public void setStore(Store pmStore) {
    	
    	if (sameAsFormer(pmStore)) {
			return;
		}
    	
        Store oldStore = this.store;
        this.store = pmStore;
        
        if (oldStore != null) {
			oldStore.removeProduct(this);
		}
        
        if (pmStore != null) {
			pmStore.addProduct(this);
		}
    }
    
    private boolean sameAsFormer(Store store) {
    	return this.store == null ?
    				store == null :
    					this.store.equals(store);
    }

    /**
     * asd.
     * @return asd.
     */
    public Delivery getDelivery() {
        return delivery;
    }

    /**
     * asd.
     * @param delivery asd
     */
    public void setDelivery(Delivery delivery) {
        
        if (sameAsFormer(delivery)) {
			return;
		}
        
        Delivery oldDelivery = this.delivery;
        this.delivery = delivery;
        
        if (oldDelivery != null) {
			oldDelivery.removeProduct(this);
		}
        
        if (delivery != null) {
			delivery.addProduct(this);
		}
    }
    
    private boolean sameAsFormer(Delivery delivery) {
    	return this.delivery == null ? 
    				delivery == null :
    					this.delivery.equals(delivery);
    }

    /**
     * asd.
     * @return asd.
     */
    public Sale getSale() {
        return sale;
    }

    /**
     * asd.
     * @param sale asd.
     */
    public void setSale(Sale sale) {
        
    	if (sameAsFormer(sale)) {
			return;
		} 
    	
    	Sale oldSale = this.sale;
    	this.sale = sale;
    	
    	if (oldSale != null) {
			sale.removeProduct(this);
		}
    	
    	if (sale != null) {
			sale.addProduct(this);
		}
    }

    private boolean sameAsFormer(Sale sale) {
		return this.sale == null ? 
					sale == null :
						this.sale.equals(sale);
	}

	
    /**
     * asd.
     * @return asd.
     */
    public Model getModel() {
        return model;
    }

    /**
     * asd.
     * @param model asd.
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * asd.
     * @return asd.
     */
    public String getColor() {
        return color;
    }

    /**
     * asd.
     * @param color asd.
     */
    public void setColor(String color) {
        this.color = color;
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

        Product product = (Product) o;

        if (color != null ? !color.equals(product.color) : product.color != null) {
            return false;
        }
        if (delivery != null ? !delivery.equals(product.delivery) : product.delivery != null) {
            return false;
        }
        if (imei != null ? !imei.equals(product.imei) : product.imei != null) {
            return false;
        }
        if (model != null ? !model.equals(product.model) : product.model != null) {
            return false;
        }
        
        if (sale != null ? !sale.equals(product.sale) : product.sale != null) {
            return false;
        }
        if (store != null ? !store.equals(product.store) : product.store != null) {
            return false;
        }

        return true;
    }

    // TODO : fix the hash code - currently sale and delivery properties are missed
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        
        
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
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
	public Collection<Tax> getTaxes() {
		return taxes;
	}

	/**
	 * asd.
	 * @param taxes a.
	 */
	public void setTaxes(Collection<Tax> taxes) {
		this.taxes = taxes;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Collection<Pricing> getPricings() {
		return pricings;
	}

	/**
	 * ads.
	 * @param pricings a.
	 */
	public void setPricings(Collection<Pricing> pricings) {
		this.pricings = pricings;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Double getPriceIn() {
		return priceIn;
	}

	/**
	 * ads.
	 * @param priceIn a.
	 */
	public void setPriceIn(Double priceIn) {
		this.priceIn = priceIn;
	}
}
