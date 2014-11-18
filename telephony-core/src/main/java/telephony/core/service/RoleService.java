package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.query.filter.RoleFilterCriteria;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public interface RoleService extends BasicService<Role> {

	/**
	 * asd.
	 * @param session TODO
	 * @param filters TODO
	 * @return asd.
	 * @throws telephony.core.service.exception.SessionServiceException
	 */
	List<Role> find(SessionDto session, RoleFilterCriteria filters) 
		throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param newrole asd.
	 * @throws SessionServiceException asd.
	 * @throws telephony.core.service.exception.RoleServiceException asd.
	 */
	void add(SessionDto session, Role newrole)
		throws SessionServiceException, RoleServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param roleToDelete asd.
	 * @throws SessionServiceException asd.
	 * @throws RoleServiceException asd.
	 */
	void remove(SessionDto session, Role roleToDelete) 
		throws SessionServiceException, RoleServiceException;


	/**
	 * asd.
	 * @param session TODO
	 * @param string asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	Role findByLabel(SessionDto session, String string) 
		throws SessionServiceException;
}
