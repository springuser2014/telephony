package telephony.core.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.SessionService;
import telephony.core.service.UserService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


/**
 * Users management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
public class UserServiceImpl extends AbstractBasicService<User> implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    private UsersDao usersDao;
    
    @Inject
    private SessionService sessionService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public final List<User> findAllUsers(String username, String sessionId) 
    		throws SessionServiceException {
       
    	logger.debug("UserServiceImpl.findAllUsers starts");
        
        Session sessionToValidate = Session.create(username, sessionId);
        sessionService.validate(sessionToValidate);

        List<User> res = usersDao.find();

        logger.debug("found {} elements ", res.size());

        return res;
    } 
    
    /**
     * {@inheritDoc}
     */
	@Override
	@Transactional
	public List<User> findUsersByStoreId(String username, String sessionId,
			Long storeId) throws SessionServiceException {
		
		logger.debug("UserServiceImpl.findUsersByStoreId starts");
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		List<User> res = usersDao.findByStoreId(storeId);
		
		logger.debug("found {} elements ", res.size());
		
		return res;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	@Transactional
	public void deleteUserById(String username, String sessionId, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.deleteUserById starts");
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
			
		usersDao.remove(user);
		
	}

	/**
     * {@inheritDoc}
     */
	@Override
	@Transactional
	public void addUser(String username, String sessionId, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.addUser starts");
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		usersDao.saveOrUpdate(user);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	@Transactional
	public void updateUser(String username, String sessionId, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.updateUser starts");
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		usersDao.saveOrUpdate(user);
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	@Transactional
	public long count() {
		
		return usersDao.count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public User findByName(String username) {
		logger.debug("UserServiceImpl.findByName starts");		
		logger.debug("params : [ username : {} ]", username);

		
		return usersDao.findByName(username);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void addRoles(String username, String sessionId, User user,
			List<Role> rolesToAdd) throws SessionServiceException {
		
		logger.debug("UserServiceImpl.addRoles starts");
		logger.debug("params : [username : {}, sessionId : {}, user : {}, rolesToAdd : {}]", 
				new Object[] {username, sessionId, user, rolesToAdd});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		for (Role r : rolesToAdd) {
			if (!user.getRoles().contains(r)) {
				user.getRoles().add(r);
			}
		}
		
		usersDao.saveOrUpdate(user);
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	@Transactional
	public void deleteRoles(String username, String sessionId, User user,
			Set<Role> rolesToDelete) throws SessionServiceException {
		
		logger.debug("UserServiceImpl.addRoles starts");
		logger.debug("params : [username : {}, sessionId : {}, user : {}, rolesToAdd : {}]", 
				new Object[] {username, sessionId, user, rolesToDelete});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
//		List<UserRole> entities = userRolesDao.findByUserAndRole(user, rolesToDelete);
//		userRolesDao.remove(entities);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void addStores(String username, String sessionId, User user,
			List<Store> storesToAdd) throws SessionServiceException {
		
		logger.debug("UserServiceImpl.addRoles starts");
		logger.debug("params : [username : {}, sessionId : {}, user : {}, rolesToAdd : {}]", 
				new Object[] {username, sessionId, user, storesToAdd});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		for (Store s : storesToAdd) {
			if (!user.getAllowedShops().contains(s)) {
				user.getAllowedShops().add(s);
			}
		}
		
		usersDao.saveOrUpdate(user);
	}

	/**
	 * {@inheritDoc}
	 */	
	@Override
	@Transactional
	public void deleteStores(String username, String sessionId, User user,
			Set<Store> storeToDelete) throws SessionServiceException {
		
		logger.debug("UserServiceImpl.addRoles starts");
		logger.debug("params : [username : {}, sessionId : {}, user : {}, rolesToAdd : {}]", 
				new Object[] {username, sessionId, user, storeToDelete});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
//		List<UserStore> entities = userStoresDao.findByUserAndStore(user, storeToDelete);
//		userStoresDao.remove(entities);
	}
	
}
