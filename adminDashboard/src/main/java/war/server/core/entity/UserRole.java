package war.server.core.entity;


import war.server.core.entity.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {

    @Column(name = "role_name", nullable = false, length = 20)
    private String roleName;

    public UserRole() {}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
