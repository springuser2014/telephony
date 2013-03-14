package telephony.core.dao;


import java.util.Date;
import java.util.List;

import telephony.core.entity.jpa.User;

/**
 * Defines basic operations on User entity.
 *
 * @author gam3r
 */
public interface UsersDao extends GenericDao<User> {

	/**
	 * Looking for a User with specified name.
	 *
	 * @param name
	 *            User's name
	 * @return User or null
	 */
    User findByName(String name);


	/**
	 * Looking for User with specified name and password.
	 *
	 * @param name
	 *            User's name
	 * @param password
	 *            User's password
	 * @return User or null
	 */
    User findByNameAndPassword(String name, String password);

	/**
	 * Updates user's session validity.
	 *
	 * @param userId
	 *            User's Id
	 * @param sessionId
	 *            User's session Id
	 * @param sessionValidity
	 *            Session expiration date
	 * @return asd.
	 */
	boolean updateSession(Long userId, String sessionId, Date sessionValidity);

	/**
	 * Looking for a User with specified name and sessionId.
	 *
	 * @param username asd.
	 * @param sessionId asd.
	 * @return asd.
	 */
    User findByNameAndSessionId(String username, String sessionId);
}
