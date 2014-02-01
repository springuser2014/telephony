package telephony.core.entity.jpa;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * ads.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class Producer extends BaseEntity {

	@Id
	@Column(name = "id" , updatable = false, nullable = false)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "producers_seq" 
	)
	@SequenceGenerator(
			name = "producers_seq",
			sequenceName = "producers_seq",
			allocationSize = 1
	)
	private Long id;
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

}
