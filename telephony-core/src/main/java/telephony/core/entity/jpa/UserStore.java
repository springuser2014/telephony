
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
 *
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
@Entity
@Table(name = "user_stores")
public class UserStore extends BaseEntity {

    /**
     * asd.
     */
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_stores_seq")
    @SequenceGenerator(
        name = "user_stores_seq",
        sequenceName = "user_stores_seq",
        allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    
    /**
     * asd. 
     * @return asd.
     */
    public User getUser() {
		return user;
	}

    /**
     * asd. 
     * @param user asd.
     */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * ads. 
	 * @return asd.
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * asd.
	 * @param store asd.
	 */
	public void setStore(Store store) {
		this.store = store;
	}

	/**
     * asd.
     */
    public UserStore() {

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
     * @param iD asd.
     */
    @Override
    public void setId(Long iD) {

        this.id = iD;
    }
}
