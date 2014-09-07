package telephony.core.dao;


import java.util.Date;
import java.util.List;

import telephony.core.entity.jpa.User;
import telephony.core.query.filter.UserFilterCriteria;

/**
 * Defines basic operations on User entity.
 */
public interface UsersDao extends GenericDao<User> {

	/**
	 * Looks for a User with specified name.
	 *
	 * @param name User's name
	 * @return User or null
	 */
    User findByName(String name);

	/**
	 * Looks for User with specified name and password.
	 *
	 * @param name User's name
	 * @param password User's password
	 * @return User or null
	 */
    User findByNameAndPassword(String name, String password);

	/**
	 * Updates user's session validity.
	 *
	 * @param userId User's Id
	 * @param sessionId User's sessionId
	 * @param sessionValidity Session's expiration date
	 * @return asd.
	 */
	boolean updateSession(Long userId, String sessionId, Date sessionValidity);

	/**
	 * Looks for a User with specified name and sessionId.
	 *
	 * @param username User's name.
	 * @param sessionId User's sessionId.
	 * @return User or null.
	 */
    User findByNameAndSessionId(String username, String sessionId);

    /**
     * Looks for a set of User which is connected to specified store.
     * @param storeId store's identificator.
     * @return a list of User.
     */
	List<User> findByStoreId(Long storeId);

	List<User> find(UserFilterCriteria filters);


}
