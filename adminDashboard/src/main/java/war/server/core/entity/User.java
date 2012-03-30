package war.server.core.entity;


import war.server.core.entity.common.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_to_store",
               joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name ="store_id", referencedColumnName = "id"))
    private Set<Store> allowedShops = new HashSet<Store>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_to_user",
               joinColumns =  @JoinColumn(name = "user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"))
    private Set<UserRole> roles = new HashSet<UserRole>();

    public User() {}

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

    public Set<Store> getAllowedShops() {
        return allowedShops;
    }

    public void setAllowedShops(Set<Store> allowedShops) {
        this.allowedShops = allowedShops;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}

