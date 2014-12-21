package telephony.core.service.converter;

import telephony.core.entity.jpa.Role;
import telephony.core.service.dto.RoleDto;

public final class RoleConverter {

    public static RoleDto toDto(Role r) {
        RoleDto dto = new RoleDto();

        dto.setId(r.getId());
        dto.setLabel(r.getName());

        return dto;
    }

    public static Role toEntity(RoleDto roleDto) {
        Role r = new Role();

        r.setName(roleDto.getLabel());

        return r;
    }
}
