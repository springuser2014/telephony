package telephony.core.service;

import java.util.List;

import telephony.core.entity.jpa.Role;
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
	List<Role> fetchAll(String username, String sessionId) throws SessionServiceException;
}
