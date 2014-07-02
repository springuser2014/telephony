package telephony.core.entity.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * asd.
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Id
    @Column(
		name = "id", 
		updatable = false, 
		nullable = false)
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "roles_seq")
    @SequenceGenerator(
		name = "roles_seq", 
		sequenceName = "roles_seq", 
		allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY, 
    			cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "store_roles",
    		joinColumns = @JoinColumn(
				name = "role_id", 
				referencedColumnName = "id"),
    		inverseJoinColumns = @JoinColumn(
				name = "store_id", 
				referencedColumnName = "id")
    )
    private Set<Store> store = new HashSet<Store>();
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, 
    			mappedBy = "roles")
    private Set<User> users = new HashSet<User>();
    
    /**
     * asd.
     * @return asd.
     */
    public Set<User> getUsers() {
		return users;
	}

    /**
     * asd.
     * @param users asd.
     */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
     * asd.
     * @return asd.
     */
	public Set<Store> getStore() {
		return store;
	}

	/**
	 * asd.
	 * @param store asd.
	 */
	public void setStore(Set<Store> store) {
		this.store = store;
	}
    
    /**
     * asd.
     * @param store asd.
     */
    public void addStore(Store store) {
    	
    }

    /**
     * asd.
     * @param store asd.
     */
	public void removeStore(Store store) {
		
	}
	
    /**
     * asd.
     * @param user asd.
     */
    public void addUser(User user) {
    	
    }

    /**
     * asd.
     * @param user asd.
     */
	public void removeUser(User user) {
		
	}

    /**
     * asd.
     */
    public Role() {

    }

    /**
     * asd.
     * @return asd.
     */
    public String getName() {
        return name;
    }

    /**
     * asd.
     * @param name asd.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * asd.
     * @param o asd.
     * @return asd.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Role role = (Role) o;

        if (name != null ? !name.equals(role.name) : role.name != null) {
            return false;
        }

        return true;
    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * asd.
     * @param id asd.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }    
}
