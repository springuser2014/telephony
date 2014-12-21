package telephony.core.service;

import java.util.List;
import java.util.Set;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;
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
	UsersFetchResponse fetch(UsersFetchRequest req) throws SessionServiceException;
	
    /**
     * asd.
     * @param req a.
     * @return a.
     * @throws telephony.core.service.exception.SessionServiceException a.
     * @throws UserServiceException a.
     */
    UserEditResponse edit(UserEditRequest req)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * asd.
     * @param req a. 
     * @return a.
     * @throws SessionServiceException a. 
     * @throws UserServiceException a.
     */
    UserAddResponse add(UserAddRequest req)
    		throws SessionServiceException, UserServiceException;
    
    /**
     * asd.
     * @param req a.
     * @throws SessionServiceException a.
     * @throws UserServiceException a.
     */
    UserDeleteResponse delete(UserDeleteRequest req)
    		throws SessionServiceException, UserServiceException;

	/**
	 * asd.
	 * @param req d.
	 * @return d.
	 */
	UserEditRoleResponse editRoles(UserEditRoleRequest req);

	/**
	 * ad.
	 * @param request a.
	 * @return a.
	 */
	UserEditStoreResponse editStores(UserEditStoreRequest request);

}
