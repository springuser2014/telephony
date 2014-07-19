package telephony.core.entity.jpa;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * asd.
 */
@Entity
@Table(name = "models")
public class Model extends BaseEntity {

	@Id
	@Column(name = "id" , updatable = false, nullable = false)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "models_seq" 
	)
	@SequenceGenerator(
			name = "models_seq",
			sequenceName = "models_seq",
			allocationSize = 1
	)
	private Long id;
	
	@Column(name = "label", nullable = false)
	private String label;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "producer_id", nullable = false)
	private Producer producer;
	
    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    private Set<Product> products;
	
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

	/**
	 * asd.
	 * @return asd.
	 */
	public Producer getProducer() {
		return producer;
	}

	/**
	 * asd.
	 * @param producer asd.
 	 */
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
}
