package telephony.core.entity.jpa;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pricings")
public class Pricing extends BaseEntity {
		
    @Id
    @Column(
		name = "id", 
		updatable = false, 
		nullable = false)
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "pricings_seq")
    @SequenceGenerator(
		name = "pricings_seq", 
		sequenceName = "pricings_seq", 
		allocationSize = 1)
    private Long id;    

    @Column(name = "from_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;    

    @Column(name = "to_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date to;
    
    @Column(name = "rate", precision = 2)
    private Double rate;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

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
	 * @return a.
	 */
	public Date getFrom() {
		return from;
	}

	/**
	 * asd.
	 * @param from a.
	 */
	public void setFrom(Date from) {
		this.from = from;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Date getTo() {
		return to;
	}

	/**
	 * asd.
	 * @param to a.
	 */
	public void setTo(Date to) {
		this.to = to;
	}
	
	/**
	 * asd.
	 * @return as.
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * asd.
	 * @param rate a.
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
