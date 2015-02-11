package telephony.core.entity.jpa;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToMany(
            mappedBy = "user",
            fetch    = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private Collection<Delivery> deliveries;

    @OneToMany(
            mappedBy = "user",
            fetch    = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private Collection<Sale> sales;

    public User() { }

    public String getSessionId() {
        return sessionId;
    }

    public Boolean getIsActive() {
		return isActive;
	}

    public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getSessionValidity() {
        return sessionValidity;
    }

    public void setSessionValidity(Date sessionValidity) {
        this.sessionValidity = sessionValidity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
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
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + "]";
	}

    public void addDelivery(Delivery delivery) {

        if (!this.deliveries.contains(delivery)) {
            deliveries.add(delivery);
        }
    }

    public void removeDelivery(Delivery delivery) {

        if (this.deliveries.contains(delivery)) {
            deliveries.remove(delivery);
        }
    }

    public Collection<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Collection<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void addSale(Sale sale) {

        if (!this.sales.contains(sale)) {
            sales.add(sale);
        }
    }

    public void removeSale(Sale sale) {

        if (this.sales.contains(sale)) {
            sales.remove(sale);
        }
    }

    public Collection<Sale> getSales() {
        return sales;
    }

    public void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }
}

