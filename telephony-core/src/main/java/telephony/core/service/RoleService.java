package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.query.filter.RoleFilterCriteria;
import telephony.core.service.dto.request.RoleFetchRequest;
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

	////////////////////////////////
	// TODO remove the stuff below
	////////////////////////////////

	/**
	 * asd.
	 * @param session TODO
	 * @param filters TODO
	 * @return asd.
	 * @throws SessionServiceException
	 */
	@Deprecated
	List<Role> find(SessionDto session, RoleFilterCriteria filters) 
		throws SessionServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param newrole asd.
	 * @throws SessionServiceException asd.
	 * @throws RoleServiceException asd.
	 */
	@Deprecated
	void add(SessionDto session, Role newrole)
		throws SessionServiceException, RoleServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param roleToDelete asd.
	 * @throws SessionServiceException asd.
	 * @throws RoleServiceException asd.
	 */
	@Deprecated
	void remove(SessionDto session, Role roleToDelete)
		throws SessionServiceException, RoleServiceException;


	/**
	 * asd.
	 * @param session TODO
	 * @param string asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	@Deprecated
	Role findByLabel(SessionDto session, String string) 
		throws SessionServiceException;
}
