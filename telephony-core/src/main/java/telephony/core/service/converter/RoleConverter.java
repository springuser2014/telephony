package telephony.core.service.converter;

import telephony.core.entity.jpa.Role;
import telephony.core.service.dto.RoleDto;

public class RoleConverter {

    public RoleDto toDto(Role r) {
        RoleDto dto = new RoleDto();

        dto.setId(r.getId());
        dto.setLabel(r.getName());

        return dto;
    }

    public Role toEntity(RoleDto roleDto) {
        Role r = new Role();

        r.setName(roleDto.getLabel());

        return r;
    }
}
