package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEditDto extends UserAddDto {

    List<Long> rolesToRemove;

    public UserEditDto() {
        this.rolesToRemove = new ArrayList<>();
    }

    public void addRoleToRemove(Long roleId) {

        if (!rolesToRemove.contains(roleId)) {
            rolesToRemove.add(roleId);
        }
    }

    public void removeRoleToRemove(Long roleId) {

        if (rolesToRemove.contains(roleId)) {
            rolesToRemove.remove(roleId);
        }
    }

    public List<Long> getRolesToRemove() {
        return rolesToRemove;
    }

    public void setRolesToRemove(List<Long> rolesToRemove) {
        this.rolesToRemove = rolesToRemove;
    }
}
