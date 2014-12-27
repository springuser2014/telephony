package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.RolesDao;
import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.User;
import telephony.core.service.dto.UserAddDto;
import telephony.core.service.dto.UserDto;
import telephony.core.service.dto.UserFetchDto;

import java.util.Collection;

public class UserConverter {

    @Inject
    RolesDao rolesDao;

    @Inject
    UsersDao usersDao;

    public UserFetchDto toDto(User entity) {
        UserFetchDto dto = new UserFetchDto();
        dto.setEmail(entity.getEmail());
        dto.setId(entity.getId());
        dto.setIsActive(entity.getIsActive());
        dto.setPassword(entity.getPassword());
        dto.setSessionId(entity.getSessionId());
        dto.setSessionValidity(entity.getSessionValidity());

        return dto;
    }

    public User toEntity(UserAddDto dto) {
        User u = new User();
        u.setEmail(dto.getEmail());
        u.setIsActive(dto.getIsActive());
        u.setPassword(dto.getPassword());
        u.setSessionId(dto.getSessionId());
        u.setSessionValidity(dto.getSessionValidity());

        Collection<Role> roles = rolesDao.findByIds(dto.getRoles());

        u.setRoles(roles);

        return u;
    }

    public void updateEntity(User user, UserDto userDto) {
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setIsActive(userDto.getIsActive());
        user.setSessionId(userDto.getSessionId());
        user.setSessionValidity(userDto.getSessionValidity());
    }
}
