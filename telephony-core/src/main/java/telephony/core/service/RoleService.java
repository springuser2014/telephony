package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.query.filter.RoleFilterCriteria;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface RoleService extends BasicService<Role> {

	/**
	 * asd.
	 * @param session TODO
	 * @param filters TODO
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	List<Role> find(Session session, RoleFilterCriteria filters) 
		throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param newrole asd.
	 * @throws SessionServiceException asd.
	 * @throws RoleServiceException asd.
	 */
	void add(Session session, Role newrole)
		throws SessionServiceException, RoleServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param roleToDelete asd.
	 * @throws SessionServiceException asd.
	 * @throws RoleServiceException asd.
	 */
	void remove(Session session, Role roleToDelete) 
		throws SessionServiceException, RoleServiceException;


	/**
	 * asd.
	 * @param session TODO
	 * @param string asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	Role findByLabel(Session session, String string) 
		throws SessionServiceException;
}
