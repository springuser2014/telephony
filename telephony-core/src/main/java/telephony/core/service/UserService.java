package telephony.core.service;

import telephony.core.entity.jpa.User;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

import java.security.NoSuchAlgorithmException;

public interface UserService extends BasicService<User> {
	
	UsersFetchResponse fetch(UsersFetchRequest req)
			throws SessionServiceException;

    UserEditResponse edit(UserEditRequest req)
    		throws SessionServiceException, UserServiceException;

	UserChangePasswordResponse changePassword(UserChangePasswordRequest request)
			throws SessionServiceException, UserServiceException, NoSuchAlgorithmException;

    UserAddResponse add(UserAddRequest req)
    		throws SessionServiceException, UserServiceException;

	UserDeleteResponse delete(UserDeleteRequest req)
    		throws SessionServiceException, UserServiceException;

	UserEditRoleResponse editRoles(UserEditRoleRequest req)
			throws SessionServiceException;

	UserEditStoreResponse editStores(UserEditStoreRequest request)
			throws SessionServiceException;
}
