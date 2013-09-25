package telephony.core.service;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionException;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

/**
 * Describes basic operations on telephony's user service.
 */
public interface UserService extends BasicService<User> {

    /**
     * Looks for all current users.
     * @param username TODO
     * @param sessionId TODO
     * @throws SessionServiceException d.
     * @return List of users.
     */
    List<User> findAllUsers(String username, String sessionId) 
    		throws SessionServiceException;

    /**
     * a.
     * @param username a.
     * @param sessionId d.
     * @param storeId d.
     * @throws SessionServiceException a.
     * @return d.
     */
    List<User> findUsersByStoreId(String username, String sessionId, Long storeId) 
    		throws SessionServiceException;
    
    /**
     * asd.
     * @param username a.
     * @param sessionId d.
     * @param user d.
     * @throws SessionServiceException d.
     * @throws UserServiceException a.
     */
    void deleteUserById(String username, String sessionId, User user)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * a.
     * @param username d.
     * @param sessionId d.
     * @param user d.
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    void addUser(String username, String sessionId, User user)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * a.
     * @param username d.
     * @param sessionId a.
     * @param user w.
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    void updateUser(String username, String sessionId, User user)
    		throws SessionServiceException, UserServiceException;

    /**
     * asd.
     * @param username asd.
     * @return asd. 
     */
	User findByName(String username);
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param user asd.
	 * @param rolesToAdd asd.
	 * @throws SessionServiceException 
	 */
	void addRoles(String username, String sessionId, User user, List<Role> rolesToAdd) throws SessionServiceException;
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param user asd.
	 * @param rolesToDelete asd.
	 * @throws SessionServiceException 
	 */
	void deleteRoles(String username, String sessionId, User user, Set<Role> rolesToDelete) throws SessionServiceException;
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param user asd.
	 * @param storesToAdd asd.
	 * @throws SessionServiceException 
	 */
	void addStores(String username, String sessionId, User user, List<Store> storesToAdd) throws SessionServiceException;
	
	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param user asd.
	 * @param storesToDelete asd.
	 * @throws SessionServiceException 
	 */
	void deleteStores(String username, String sessionId, User user, Set<Store> storesToDelete) throws SessionServiceException;
    
}
