package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

import java.util.ArrayList;
import java.util.List;

public class UserEditRoleRequest extends AuthRequest {

    Long userId;
    List<Long> rolesToAdd;
    List<Long> rolesToDelete;

    public UserEditRoleRequest() {
        super();
        this.rolesToAdd = new ArrayList<Long>();
        this.rolesToDelete = new ArrayList<Long>();
    }

    public UserEditRoleRequest(SessionDto sessionDto) {
        super(sessionDto);
        this.rolesToAdd = new ArrayList<Long>();
        this.rolesToDelete = new ArrayList<Long>();
    }

    public void addRoleToAdd(Long roleId) {

        if (!this.rolesToAdd.contains(roleId)) {
            this.rolesToAdd.add(roleId);
        }
    }

    public void removeRoleToAdd(Long roleId) {

        if (this.rolesToAdd.contains(roleId)) {
            this.rolesToAdd.remove(roleId);
        }
    }

    public void addRoleToDelete(Long roleId) {

        if (!this.rolesToDelete.contains(roleId)) {
            this.rolesToDelete.add(roleId);
        }
    }

    public void removeRoleToDelete(Long roleId) {
        if (this.rolesToDelete.contains(roleId)) {
            this.rolesToDelete.remove(roleId);
        }
    }

    public List<Long> getRolesToAdd() {
        return rolesToAdd;
    }

    public List<Long> getRolesToDelete() {
        return rolesToDelete;
    }

    public void setRolesToAdd(List<Long> rolesToAdd) {
        this.rolesToAdd = rolesToAdd;
    }

    public void setRolesToDelete(List<Long> rolesToDelete) {
        this.rolesToDelete = rolesToDelete;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
