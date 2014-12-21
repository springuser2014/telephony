package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class RoleDeleteRequest extends AuthRequest {

    Long roleId;

    public RoleDeleteRequest() {
        super();
    }

    public RoleDeleteRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
