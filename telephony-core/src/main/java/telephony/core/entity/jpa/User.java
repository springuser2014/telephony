package telephony.core.entity.jpa;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * asd.
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    private Long id;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "session_id", nullable = true, length = 32)
    private String sessionId;
    
    @Column(name = "isactive", nullable = false)
    private Boolean isActive;

    @Column(name = "session_validity", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionValidity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
		name = "user_stores",
        joinColumns = @JoinColumn(
    		name = "user_id", 
    		referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
    		name = "store_id", 
    		referencedColumnName = "id"))
    private Set<Store> allowedShops;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    /**
     * asd.
     */
    public User() {

    }

    /**
     * asd.
     * @return asd.
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * asd.
     * @return a.
     */
    public Boolean getIsActive() {
		return isActive;
	}

    /**
	 * asd.
     * @param isActive fo.
     */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
     * asd.
     * @param sessionId asd.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * asd.
     * @return asd.
     */
    public Date getSessionValidity() {
        return sessionValidity;
    }

    /**
     * asd.
     * @param sessionValidity asd.
     */
    public void setSessionValidity(Date sessionValidity) {
        this.sessionValidity = sessionValidity;
    }

    /**
     * asd.
     * @return asd.
     */
    public String getEmail() {
        return email;
    }


    /**
     * asd.
     * @param email asd.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * asd.
     * @return asd.
     */
    public String getPassword() {
        return password;
    }

    /**
     * asd.
     * @param password asd.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * asd.
     * @return asd.
     */
    public Set<Store> getAllowedShops() {
        return allowedShops;
    }

    /**
     * asd.
     * @param allowedShops asd.
     */
    public void setAllowedShops(Set<Store> allowedShops) {
        this.allowedShops = allowedShops;
    }

    /**
     * asd.
     * @return asd.
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * asd.
     * @param roles asd.
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (allowedShops != null
                        ? !allowedShops.equals(user.allowedShops) : user.allowedShops != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }
        if (roles != null ? !roles.equals(user.roles) : user.roles != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (allowedShops != null ? allowedShops.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + "]";
	}    
}

