package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFetchDto extends UserDto {

    List<RoleDto> roles;

    public UserFetchDto() {
        this.roles = new ArrayList<RoleDto>();
    }

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
