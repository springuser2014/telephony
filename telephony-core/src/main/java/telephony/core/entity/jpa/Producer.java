package telephony.core.entity.jpa;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "producers")
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
	
	@Column(name = "label", nullable = false)
	private String label;
	
	@OneToMany(mappedBy = "producer")
	private Collection<Model> models;
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Collection<Model> getModels() {
		return models;
	}

	public void setModels(Collection<Model> models) {
		this.models = models;
	}
}
