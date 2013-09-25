package telephony.core.entity.jpa;


import javax.persistence.CascadeType;
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
@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {

//    @Id
//    @Column(name = "id", updatable = false, nullable = false)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_roles_seq")
//    @SequenceGenerator(name = "user_roles_seq", sequenceName = "user_roles_seq", allocationSize = 1)
//    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    /**
     * asd.
     */
    public UserRole() {

    }

    @Override
    public Long getId() {
    	throw new UnsupportedOperationException();
//        return this.id;
    }

    @Override
    public void setId(Long id) {
    	throw new UnsupportedOperationException();
//        this.id = id;
    }

    /***
     * asd.
     * @return asd.
     */
    public User getUser() {
        return user;
    }

    /**
     * asd.
     * @param userId asd.
     */
    public void setUser(User userId) {
        this.user = userId;
    }

    /**
     * asd.
     * @return asd.
     */
    public Role getRole() {
        return role;
    }

    /**
     * asd.
     * @param roleId asd.
     */
    public void setRole(Role roleId) {
        this.role = roleId;
    }

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

        UserRole userRole = (UserRole) o;

//        if (!id.equals(userRole.id)) {
//            return false;
//        }
        if (!role.equals(userRole.role)) {
            return false;
        }
        if (!user.equals(userRole.user)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
//        result = 31 * result + id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
