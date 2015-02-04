package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFetchDto extends UserDto {

    List<RoleDto> roles;

    Boolean editable;
    Boolean deletable;

    public UserFetchDto() {
        this.roles = new ArrayList<>();
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getDeletable() {
        return deletable;
    }

    public void setDeletable(Boolean deletable) {
        this.deletable = deletable;
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
