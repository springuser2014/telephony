package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAddDto extends UserDto {

    String password1;
    String password2;
    List<Long> rolesToAdd;

    public UserAddDto() {
        super();

        rolesToAdd = new ArrayList<>();
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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
