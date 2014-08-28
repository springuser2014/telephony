package telephony.core.service;

import java.util.List;
import java.util.Set;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

/**
 * Describes basic operations on telephony's user service.
 */
public interface UserService extends BasicService<User> {

    /**
     * Looks for all current users.
     * @param session TODO
     * @throws SessionServiceException d.
     * @return List of users.
     */
    List<User> find(Session session) 
    		throws SessionServiceException;

    /**
     * a.
     * @param session TODO
     * @param storeId d.
     * @throws SessionServiceException a.
     * @return d.
     */
    List<User> findUsersByStoreId(Session session, Long storeId) 
    		throws SessionServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param user d.
     * @throws SessionServiceException d.
     * @throws UserServiceException a.
     */
    void deleteUserById(Session session, User user)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * a.
     * @param session TODO
     * @param user d.
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    void addUser(Session session, User user)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * a.
     * @param session TODO
     * @param user w.
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    void updateUser(Session session, User user)
    		throws SessionServiceException, UserServiceException;

    /**
     * asd.
     * @param session TODO
     * @param username asd.
     * @return asd. 
     */
	User findByName(Session session, String username);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param rolesToAdd asd.
	 * @throws SessionServiceException 
	 */
	void addRoles(Session session, User user, List<Role> rolesToAdd) 
			throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param rolesToDelete asd.
	 * @throws SessionServiceException 
	 */
	void deleteRoles(Session session, User user, Set<Role> rolesToDelete) 
			throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param storesToAdd asd.
	 * @throws SessionServiceException 
	 */
	void addStores(Session session, User user, List<Store> storesToAdd) 
			throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param storesToDelete asd.
	 * @throws SessionServiceException 
	 */
	void deleteStores(Session session, User user, Set<Store> storesToDelete) 
			throws SessionServiceException;
    
}
