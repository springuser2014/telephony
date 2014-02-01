package telephony.core.entity.jpa;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

/**
 * asd.
 *
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "complaint_type")
@Table(name = "complaints")
public abstract class Complaint extends BaseEntity {
	
	@Id
	private Long id;
	
	@Column(name = "description")
	private String description;
	
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
	public String getDescription() {
		return description;
	}

	/**
	 * asd.
	 * @param description asd.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}