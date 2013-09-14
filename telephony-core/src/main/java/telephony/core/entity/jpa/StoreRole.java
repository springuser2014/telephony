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


@Entity
@Table(name = "store_roles")
public class StoreRole extends BaseEntity {
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "store_roles_seq")
	@SequenceGenerator(
			name = "store_roles_seq",
			sequenceName = "store_roles_seq",
			allocationSize = 1)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	/**
	 * asd.
	 */
	public StoreRole() {
		
	}

	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Role getRole() {
		return role;
		
	}

	public void setRole(Role role) {
		this.role= role;
	}

	@Override
	public String toString() {
		return "StoreRole [id=" + id + ", store=" + store + ", role=" + role
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreRole other = (StoreRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

}
