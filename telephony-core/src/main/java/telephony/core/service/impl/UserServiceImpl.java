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
import telephony.core.service.dto.Session;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


/**
 * Users management service.
 */
public class UserServiceImpl extends AbstractBasicService<User> implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    private UsersDao usersDao;
    
    @Inject
    private SessionService sessionService;

    @Override
    @Transactional
    public final List<User> find(Session session) 
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
	public List<User> findUsersByStoreId(Session session, Long storeId) 
			throws SessionServiceException {
		
		logger.debug("UserServiceImpl.findUsersByStoreId starts");
		
		sessionService.validate(session);
		
		List<User> res = usersDao.findByStoreId(storeId);
		
		logger.debug("found {} elements ", res.size());
		
		return res;
	}

	@Override
	@Transactional
	public void deleteUserById(Session session, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.deleteUserById starts");
		
		sessionService.validate(session);
			
		usersDao.remove(user);
		
	}

	@Override
	@Transactional
	public void addUser(Session session, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.addUser starts");
		
		sessionService.validate(session);
		
		usersDao.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void updateUser(Session session, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.updateUser starts");
		
		sessionService.validate(session);
		
		usersDao.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public long count(Session session) {
		
		return usersDao.count();
	}


	@Override
	@Transactional
	public User findByName(Session session, String username) {
		logger.debug("UserServiceImpl.findByName starts");		
		logger.debug("params : [ username : {} ]", username);

		
		return usersDao.findByName(username);
	}

	@Override
	@Transactional
	public void addRoles(Session session, User user, List<Role> rolesToAdd) 
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
	public void deleteRoles(Session session, User user, Set<Role> rolesToDelete) 
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
	public void addStores(Session session, User user, List<Store> storesToAdd) 
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
	public void deleteStores(Session session, User user, Set<Store> storeToDelete) 
			throws SessionServiceException {
		
		logger.debug("UserServiceImpl.addRoles starts");
		logger.debug("params : [ session : {}, user : {}, rolesToAdd : {}]", 
				new Object[] {session, user, storeToDelete});

		sessionService.validate(session);
		
		user.getAllowedShops().removeAll(storeToDelete);
		usersDao.saveOrUpdate(user);
	}
	
}
