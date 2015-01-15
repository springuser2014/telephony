package telephony.core.service.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAddDto extends UserDto {

    String password;
    List<Long> rolesToAdd;

    public UserAddDto() {
        super();

        rolesToAdd = new ArrayList<Long>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getRolesToAdd() {
        return rolesToAdd;
    }

    public void setRolesToAdd(List<Long> rolesToAdd) {
        this.rolesToAdd = rolesToAdd;
    }

    public void addRoleToAdd(Long roleId) {

        if (!rolesToAdd.contains(roleId)) {
            rolesToAdd.add(roleId);
        }
    }

    public void removeRoleToAdd(Long roleId) {

        if (rolesToAdd.contains(roleId)) {
            rolesToAdd.remove(roleId);
        }
    }
}
