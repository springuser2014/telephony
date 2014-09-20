package telephony.core.service;

import java.util.List;
import java.util.Set;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.dto.SessionDto;
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
    List<User> find(SessionDto session) 
    		throws SessionServiceException;

    /**
     * a.
     * @param session TODO
     * @param storeId d.
     * @throws SessionServiceException a.
     * @return d.
     */
    List<User> findUsersByStoreId(SessionDto session, Long storeId) 
    		throws SessionServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param user d.
     * @throws SessionServiceException d.
     * @throws UserServiceException a.
     */
    void deleteUserById(SessionDto session, User user)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * a.
     * @param session TODO
     * @param user d.
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    void addUser(SessionDto session, User user)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * a.
     * @param session TODO
     * @param user w.
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    void updateUser(SessionDto session, User user)
    		throws SessionServiceException, UserServiceException;

    /**
     * asd.
     * @param session TODO
     * @param username asd.
     * @return asd. 
     */
	User findByName(SessionDto session, String username);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param rolesToAdd asd.
	 * @throws SessionServiceException 
	 */
	void addRoles(SessionDto session, User user, List<Role> rolesToAdd) 
			throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param rolesToDelete asd.
	 * @throws SessionServiceException 
	 */
	void deleteRoles(SessionDto session, User user, Set<Role> rolesToDelete) 
			throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param storesToAdd asd.
	 * @throws SessionServiceException 
	 */
	void addStores(SessionDto session, User user, List<Store> storesToAdd) 
			throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param storesToDelete asd.
	 * @throws SessionServiceException 
	 */
	void deleteStores(SessionDto session, User user, Set<Store> storesToDelete) 
			throws SessionServiceException;
    
}
