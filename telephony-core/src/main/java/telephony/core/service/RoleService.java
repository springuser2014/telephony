package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.query.filter.RoleFilterCriteria;
import telephony.core.service.dto.request.RoleAddRequest;
import telephony.core.service.dto.request.RoleDeleteRequest;
import telephony.core.service.dto.request.RoleFetchRequest;
import telephony.core.service.dto.response.RoleAddResponse;
import telephony.core.service.dto.response.RoleDeleteResponse;
import telephony.core.service.dto.response.RoleFetchResponse;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public interface RoleService extends BasicService<Role> {

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 * @throws SessionServiceException a.
	 * @throws RoleServiceException a.
	 */
	RoleFetchResponse fetch(RoleFetchRequest request)
		throws SessionServiceException, RoleServiceException;

	/**
	 * asd.
	 * @param request d.
	 * @return d.
	 * @throws SessionServiceException d.
	 * @throws RoleServiceException d.
	 */
	RoleAddResponse add(RoleAddRequest request)
		throws SessionServiceException, RoleServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 * @throws SessionServiceException a.
	 * @throws RoleServiceException d.
	 */
	RoleDeleteResponse delete(RoleDeleteRequest request)
		throws SessionServiceException, RoleServiceException;
}
