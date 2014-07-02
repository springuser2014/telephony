package telephony.core.entity.jpa;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * asd.
 */
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
    
    @ManyToMany(mappedBy = "taxes", fetch = FetchType.LAZY)
    private Collection<Product> products;

    /**
     * as.
     * @return as.
     */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * asd.
	 * @param id a.
	 */
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
	 * ad.
	 * @return a.
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * q.
	 * @param rate a.
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}
}
