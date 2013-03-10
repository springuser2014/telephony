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
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_roles_seq")
    @SequenceGenerator(name = "user_roles_seq", sequenceName = "user_roles_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Store userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Store roleId;

    /**
     * asd.
     */
    public UserRole() {

    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /***
     * asd.
     * @return asd.
     */
    public Store getUserId() {
        return userId;
    }

    /**
     * asd.
     * @param userId asd.
     */
    public void setUserId(Store userId) {
        this.userId = userId;
    }

    /**
     * asd.
     * @return asd.
     */
    public Store getRoleId() {
        return roleId;
    }

    /**
     * asd.
     * @param roleId asd.
     */
    public void setRoleId(Store roleId) {
        this.roleId = roleId;
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

        if (!id.equals(userRole.id)) {
            return false;
        }
        if (!roleId.equals(userRole.roleId)) {
            return false;
        }
        if (!userId.equals(userRole.userId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + roleId.hashCode();
        return result;
    }
}
