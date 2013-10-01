package telephony.core.entity.jpa;



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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id", nullable = true)
    private Sale sale;

    @Column(name = "producer", length = 100, nullable = false)
    private String producer;

    @Column(name = "model", length = 100, nullable = false)
    private String model;

    @Column(name = "color", length = 20, nullable = false)
    private String color;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value",
                               column = @Column(
                                   name = "price_in",
                                   nullable = true))
    })
    private Money priceIn = new Money();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value",
                               column = @Column(
                                   name = "price_out",
                                   nullable = true))
    })
    private Money priceOut = new Money();

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
    public String getProducer() {
        return producer;
    }

    /**
     * asd.
     * @param producer asd.
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * asd.
     * @return asd.
     */
    public String getModel() {
        return model;
    }

    /**
     * asd.
     * @param model asd.
     */
    public void setModel(String model) {
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

    /**
     * asd.
     * @return asd.
     */
    public Money getPriceIn() {
        return priceIn;
    }

    /**
     * asd.
     * @param priceIn asd.
     */
    public void setPriceIn(Money priceIn) {
        this.priceIn = priceIn;
    }

    /**
     * asd.
     * @return asd.
     */
    public Money getPriceOut() {
        return priceOut;
    }

    /**
     * asd.
     * @param priceOut asd.
     */
    public void setPriceOut(Money priceOut) {
        this.priceOut = priceOut;
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
        if (priceIn != null ? !priceIn.equals(product.priceIn) : product.priceIn != null) {
            return false;
        }
        if (priceOut != null ? !priceOut.equals(product.priceOut) : product.priceOut != null) {
            return false;
        }
        if (producer != null ? !producer.equals(product.producer) : product.producer != null) {
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
        
        
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (priceIn != null ? priceIn.hashCode() : 0);
        result = 31 * result + (priceOut != null ? priceOut.hashCode() : 0);
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
}
