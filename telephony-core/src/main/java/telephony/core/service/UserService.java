package telephony.core.service;

import java.util.List;

import org.hibernate.SessionException;

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
     * @return TODO
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    User addUser(String username, String sessionId, User user)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * a.
     * @param username d.
     * @param sessionId a.
     * @param user w.
     * @return TODO
     * @throws SessionServiceException d.
     * @throws UserServiceException d.
     */
    User updateUser(String username, String sessionId, User user)
    		throws SessionServiceException, UserServiceException;

    /**
     * asd.
     * @param username asd.
     * @return
     */
	User findByName(String username);    
    
}
