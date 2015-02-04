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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
	// TODO impl
	public void addProduct(Product product) {
		
	}
	
	// TODO impl
	public void removeProduct(Product product) {
		
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
