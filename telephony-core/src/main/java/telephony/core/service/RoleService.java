package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.Role;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface RoleService extends BasicService<Role> {

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	List<Role> fetchAll(String username, String sessionId) 
			throws SessionServiceException;
	
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd. 
	 * @param newrole asd.
	 * @throws SessionServiceException asd.
	 * @throws RoleServiceException asd.
	 */
	void add(String username, String sessionId, Role newrole)
		throws SessionServiceException, RoleServiceException;
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param roleToDelete asd.
	 * @throws SessionServiceException asd.
	 * @throws RoleServiceException asd.
	 */
	void delete(String username, String sessionId, Role roleToDelete) 
		throws SessionServiceException, RoleServiceException;
	
	
}
