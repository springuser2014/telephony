package telephony.core.entity.jpa;

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

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@Table
@Entity(name = "models")
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
	
	@Override
	public Long getId() {		
		return this.id;
	}

	@Override
	public void setId(Long id) {			
		this.id = id;
	}
	
	

}
