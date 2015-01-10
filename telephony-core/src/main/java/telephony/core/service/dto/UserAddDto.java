package telephony.core.service.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAddDto extends UserDto {

    String password;
    List<Long> roles;

    public UserAddDto() {
        super();

        roles = new ArrayList<Long>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public void addRole(Long roleId) {

        if (!roles.contains(roleId)) {
            roles.add(roleId);
        }
    }

    public void removeRole(Long roleId) {

        if (roles.contains(roleId)) {
            roles.remove(roleId);
        }
    }
}
