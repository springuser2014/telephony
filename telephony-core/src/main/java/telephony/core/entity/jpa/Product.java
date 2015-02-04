package telephony.core.entity.jpa;

import java.util.Collection;
import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
    
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.ALL } )
    private Collection<ProductTax> productTaxes;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<Pricing> pricings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<ProductComplaint> complaints;
    
    public Product() {
    }

    public Collection<ProductComplaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Collection<ProductComplaint> complaints) {
        this.complaints = complaints;
    }

    public void addComplaint(ProductComplaint pc) {
        complaints.add(pc);
    }

    public void removecomplaint(ProductComplaint pc) {
        complaints.remove(pc);
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public final Store getStore() {
        return store;
    }

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

    public Delivery getDelivery() {
        return delivery;
    }

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

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        
    	if (sameAsFormer(sale)) {
			return;
		} 
    	
    	Sale oldSale = this.sale;
    	this.sale = sale;
    	
    	if (oldSale != null) {
            oldSale.removeProduct(this);
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

	public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

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
    
    public void addProductTax(ProductTax productTax) {
		
		if (!this.productTaxes.contains(productTax)) {
			this.productTaxes.add(productTax);
		}
	}
	
	public void removeProductTax(ProductTax productTax) {
		
		if (this.productTaxes.contains(productTax)) {
			this.productTaxes.remove(productTax);
		}
	}

    public Collection<ProductTax> getProductTaxes() {
		return productTaxes;
	}

	public void setProductTaxes(Collection<ProductTax> taxes) {
		this.productTaxes = taxes;
	}

	public Collection<Pricing> getPricings() {
		return pricings;
	}

	public void setPricings(Collection<Pricing> pricings) {
		this.pricings = pricings;
	}
	
	public void addPricing(Pricing pricing) {
		
		if (!this.pricings.contains(pricing)) {
			this.pricings.add(pricing);
		}
	}
	
	public void removePricing(Pricing pricing) {
		
		if (this.pricings.contains(pricing)) {
			this.pricings.remove(pricing);
		}
	}

	public Double getPriceIn() {
		return priceIn;
	}

	public void setPriceIn(Double priceIn) {
		this.priceIn = priceIn;
	}
	
	public ProductTax getCurrentTax() {
		
		long now = new Date().getTime();
		
		for(ProductTax ptax : this.productTaxes) {
			
			if (ptax.getFrom() != null && ptax.getTo() != null && (ptax.getFrom().getTime() <= now && ptax.getTo().getTime() >= now)) {				
				return ptax;
			}
			
			if (ptax.getFrom() != null && ptax.getFrom().getTime() <= now && ptax.getTo() == null) {
				return ptax;
			}
		}
		
		return null;
	}

	public Pricing getCurrentPricing() {

		long now = new Date().getTime();

		for (Pricing price : this.pricings) {
			
			if (price.getFrom() != null && price.getTo() != null && (price.getFrom().getTime() <= now && price.getTo().getTime() >= now) ) {
				
				return price;
			}
			
			if (price.getFrom() != null && (price.getFrom().getTime() <= now && price.getTo() == null)) {
				
				return price;
			}
		}
		
		return null;
	}
}
