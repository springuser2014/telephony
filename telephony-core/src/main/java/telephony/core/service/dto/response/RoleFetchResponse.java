package telephony.core.service.dto.response;

import telephony.core.service.dto.RoleDto;

import java.util.List;

public class RoleFetchResponse extends BasicResponse {
    private List<RoleDto> roles;

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public void addRole(RoleDto dto) {
        if (!roles.contains(dto)) {
            roles.add(dto);
        }
    }

    public void removeRole(RoleDto dto) {
        if (roles.contains(dto)) {
            roles.remove(dto);
        }
    }
}
