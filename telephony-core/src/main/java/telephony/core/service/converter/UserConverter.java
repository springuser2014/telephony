package telephony.core.service.converter;

import telephony.core.entity.jpa.User;
import telephony.core.service.dto.UserDto;

public final class UserConverter {

    public static UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setEmail(entity.getEmail());
        dto.setId(entity.getId());
        dto.setIsActive(entity.getIsActive());
        dto.setPassword(entity.getPassword());
        dto.setSessionId(entity.getSessionId());
        dto.setSessionValidity(entity.getSessionValidity());

        return dto;
    }

    public static User toEntity(UserDto dto) {
        User u = new User();
        u.setEmail(dto.getEmail());
        u.setIsActive(dto.getIsActive());
        u.setPassword(dto.getPassword());
        u.setSessionId(dto.getSessionId());
        u.setSessionValidity(dto.getSessionValidity());

        return u;
    }

    public static void updateEntity(User user, UserDto userDto) {
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setIsActive(userDto.getIsActive());
        user.setSessionId(userDto.getSessionId());
        user.setSessionValidity(userDto.getSessionValidity());
    }
}
