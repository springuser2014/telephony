package telephony.core.service.dto.request;

import telephony.core.service.dto.RoleDto;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public class RoleAddRequest extends AuthRequest {

	RoleDto roleDto;

	public RoleAddRequest() {
		super();
	}

	public RoleAddRequest(SessionDto sessionDto) {
		super(sessionDto);
	}

	public RoleDto getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDto roleDto) {
		this.roleDto = roleDto;
	}
}
