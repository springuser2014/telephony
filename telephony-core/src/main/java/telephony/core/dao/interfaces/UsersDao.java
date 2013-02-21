package telephony.core.dao.interfaces;


import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import telephony.core.entity.User;

/**
 * Defines basic operations on User entity.
 * 
 * @author gam3r
 */
public interface UsersDao extends GenericDao<User> {


	/**
	 * Looking for all not removed entities.
	 * 
	 * @return List of found entities.
	 */
	public List<User> findNotRemoved();

	/**
	 * Looking for a User with specified name
	 * 
	 * @param name
	 *            User's name
	 * @return User or null
	 */
    User findByName(String name);


	/**
	 * Looking for User with specified name and password
	 * 
	 * @param name
	 *            User's name
	 * @param password
	 *            User's password
	 * @return User or null
	 * @throws NoSuchElementException
	 */
    User findByNameAndPassword(String name, String password) throws NoSuchElementException;

	/**
	 * Updates user's session validity
	 * 
	 * @param userId
	 *            User's Id
	 * @param sessionId
	 *            User's session Id
	 * @param sessionValidity
	 *            Session expiration date
	 * @return
	 */
	boolean updateSession(Long userId, String sessionId, Date sessionValidity);

	/**
	 * Looking for a User with specified name and sessionId
	 * 
	 * @param username
	 * @param sessionId
	 * @return
	 */
    User findByNameAndSessionId(String username, String sessionId);
}
