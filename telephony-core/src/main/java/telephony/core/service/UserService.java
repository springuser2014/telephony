package telephony.core.service;

import java.util.List;
import java.util.Set;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.UserAddResponse;
import telephony.core.service.dto.response.UserEditResponse;
import telephony.core.service.dto.response.UsersFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

/**
 * Describes basic operations on telephony's user service.
 */
public interface UserService extends BasicService<User> {
	
	/**
	 * asd.
	 * @param req a.
	 * @return d.
	 */
	UsersFetchResponse fetch(UsersFetchRequest req);
	
    /**
     * asd.
     * @param req a.
     * @return a.
     * @throws SessionServiceException a.
     * @throws UserServiceException a.
     */
    UserEditResponse updateUser(UserEditRequest req)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * asd.
     * @param req a. 
     * @return a.
     * @throws SessionServiceException a. 
     * @throws UserServiceException a.
     */
    UserAddResponse addUser(UserAddRequest req)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * asd.
     * @param req a.
     * @throws SessionServiceException a.
     * @throws UserServiceException a.
     */
    void deleteUserById(UserDeleteRequest req)
    		throws SessionServiceException, UserServiceException;

    /**
     * Looks for all current users.
     * @param session TODO
     * @throws SessionServiceException d.
     * @return List of users.
     */
	@Deprecated
    List<User> find(SessionDto session) 
    		throws SessionServiceException;

    /**
     * a.
     * @param session TODO
     * @param storeId d.
     * @throws SessionServiceException a.
     * @return d.
     */
	@Deprecated
    List<User> findUsersByStoreId(SessionDto session, Long storeId) 
    		throws SessionServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param user d.
     * @throws SessionServiceException d.
     * @throws UserServiceException a.
     */
	@Deprecated
    void deleteUserById(SessionDto session, User user)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * a.
     * @param session TODO
     * @param user d.
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    @Deprecated
    void addUser(SessionDto session, User user)
    		throws SessionServiceException, UserServiceException;
    
    
    /**
     * a.
     * @param session TODO
     * @param user w.
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    @Deprecated
    void updateUser(SessionDto session, User user)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param username asd.
     * @return asd. 
     */
    @Deprecated
    User findByName(SessionDto session, String username);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param rolesToAdd asd.
	 * @throws SessionServiceException 
	 */
    @Deprecated
    void addRoles(SessionDto session, User user, List<Role> rolesToAdd) 
			throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param rolesToDelete asd.
	 * @throws SessionServiceException 
	 */
    @Deprecated
	void deleteRoles(SessionDto session, User user, Set<Role> rolesToDelete) 
			throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param storesToAdd asd.
	 * @throws SessionServiceException 
	 */
	@Deprecated
	void addStores(SessionDto session, User user, List<Store> storesToAdd) 
			throws SessionServiceException;
	
	/**
	 * asd.
	 * @param session TODO
	 * @param user asd.
	 * @param storesToDelete asd.
	 * @throws SessionServiceException 
	 */
	@Deprecated
	void deleteStores(SessionDto session, User user, Set<Store> storesToDelete) 
			throws SessionServiceException;
    
}
