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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "taxes")
public class Tax extends BaseEntity {
	
    @Id
    @Column(
		name = "id", 
		updatable = false, 
		nullable = false)
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "taxes_seq")
    @SequenceGenerator(
		name = "taxes_seq", 
		sequenceName = "taxes_seq", 
		allocationSize = 1)
    private Long id;
    
    @Column(name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;    

    @Column(name = "to_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date to;
    
    @Column(name = "rate")
    private Double rate;
    
    @OneToMany(mappedBy = "tax", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE } )
    private Collection<ProductTax> productTaxes;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
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

	public void setProductTaxes(Collection<ProductTax> productTaxes) {
		this.productTaxes = productTaxes;
	}
	
	
}
