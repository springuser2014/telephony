package telephony.core.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.query.filter.UserFilterCriteria;
import telephony.core.service.SessionService;
import telephony.core.service.UserService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.UserAddRequest;
import telephony.core.service.dto.request.UserEditRequest;
import telephony.core.service.dto.request.UsersFetchRequest;
import telephony.core.service.dto.response.UserAddResponse;
import telephony.core.service.dto.response.UserDeleteResponse;
import telephony.core.service.dto.response.UserEditResponse;
import telephony.core.service.dto.response.UsersFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;
import telephony.core.service.dto.request.UserDeleteRequest;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


/**
 * Users management service.
 */
public class UserServiceImpl 
extends AbstractBasicService<User> 
implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    private UsersDao usersDao;
    
    @Inject
    private SessionService sessionService;

    @Override
    @Transactional
    public final List<User> find(SessionDto session)
    		throws SessionServiceException {
       
    	logger.debug("UserServiceImpl.findAllUsers starts");
        
        sessionService.validate(session);
        UserFilterCriteria filters = UserFilterCriteria.create();
        List<User> res = usersDao.find(filters);

        logger.debug("found {} elements ", res.size());

        return res;
    } 
    
	@Override
	@Transactional
	public List<User> findUsersByStoreId(SessionDto session, Long storeId) 
			throws SessionServiceException {
		
		logger.debug("UserServiceImpl.findUsersByStoreId starts");
		
		sessionService.validate(session);
		
		List<User> res = usersDao.findByStoreId(storeId);
		
		logger.debug("found {} elements ", res.size());
		
		return res;
	}

	@Override
	@Transactional
	public void deleteUserById(SessionDto session, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.deleteUserById starts");
		
		sessionService.validate(session);
			
		usersDao.remove(user);
		
	}

	@Override
	@Transactional
	public void addUser(SessionDto session, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.addUser starts");
		
		sessionService.validate(session);
		
		usersDao.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void updateUser(SessionDto session, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.updateUser starts");
		
		sessionService.validate(session);
		
		usersDao.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public long count(SessionDto session) {
		
		return usersDao.count();
	}


	@Override
	@Transactional
	public User findByName(SessionDto session, String username) {
		logger.debug("UserServiceImpl.findByName starts");		
		logger.debug("params : [ username : {} ]", username);

		
		return usersDao.findByName(username);
	}

	@Override
	@Transactional
	public void addRoles(SessionDto session, User user, List<Role> rolesToAdd) 
			throws SessionServiceException {
		
		logger.debug("UserServiceImpl.addRoles starts");
		logger.debug("params : [username : {}, sessionId : {}, user : {}, rolesToAdd : {}]", 
				new Object[] {session, user, rolesToAdd});

		sessionService.validate(session);
		
		for (Role r : rolesToAdd) {
			if (!user.getRoles().contains(r)) {
				user.getRoles().add(r);
			}
		}
		
		usersDao.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void deleteRoles(SessionDto session, User user, Set<Role> rolesToDelete) 
			throws SessionServiceException {
		
		logger.debug("UserServiceImpl.addRoles starts");
		logger.debug("params : [ session : {}, user : {}, rolesToAdd : {}]", 
				new Object[] {session, user, rolesToDelete});

		sessionService.validate(session);
		
		user.getRoles().removeAll(rolesToDelete);
		usersDao.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void addStores(SessionDto session, User user, List<Store> storesToAdd) 
			throws SessionServiceException {
		
		logger.debug("UserServiceImpl.addRoles starts");
		logger.debug("params : [ session : {}, user : {}, rolesToAdd : {}]", 
				new Object[] {session, user, storesToAdd});

		sessionService.validate(session);
		
		for (Store s : storesToAdd) {
			if (!user.getAllowedShops().contains(s)) {
				user.getAllowedShops().add(s);
			}
		}
		
		usersDao.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void deleteStores(SessionDto session, User user, Set<Store> storeToDelete) 
			throws SessionServiceException {
		
		logger.debug("UserServiceImpl.addRoles starts");
		logger.debug("params : [ session : {}, user : {}, rolesToAdd : {}]", 
				new Object[] {session, user, storeToDelete});

		sessionService.validate(session);
		
		user.getAllowedShops().removeAll(storeToDelete);
		usersDao.saveOrUpdate(user);
	}

	@Override
	public UsersFetchResponse fetch(UsersFetchRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEditResponse updateUser(UserEditRequest req)
			throws SessionServiceException, UserServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAddResponse addUser(UserAddRequest req)
			throws SessionServiceException, UserServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDeleteResponse deleteUserById(UserDeleteRequest req)
			throws SessionServiceException, UserServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
