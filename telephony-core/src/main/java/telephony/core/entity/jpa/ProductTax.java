package telephony.core.entity.jpa;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "product_taxes")
public class ProductTax extends BaseEntity {
	
    @Id
    @Column(
		name = "id", 
		updatable = false, 
		nullable = false)
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "product_taxes_seq")
    @SequenceGenerator(
		name = "product_taxes_seq", 
		sequenceName = "product_taxes_seq", 
		allocationSize = 1)
    private Long id;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_id", nullable = false)
	private Tax tax;
	
	@Column(name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;
	
	@Column(name = "to_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date to;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
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

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
