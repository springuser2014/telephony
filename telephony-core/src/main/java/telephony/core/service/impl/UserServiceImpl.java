package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UserRolesDao;
import telephony.core.dao.UserStoresDao;
import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.entity.jpa.UserRole;
import telephony.core.entity.jpa.UserStore;
import telephony.core.service.SessionService;
import telephony.core.service.UserService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.UnitOfWork;


/**
 * Users management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
public class UserServiceImpl extends AbstractBasicService<User> implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    private UsersDao usersDao;
    
    @Inject 
    private UserRolesDao userRolesDao;
    
    @Inject
    private UserStoresDao userStoresDao;
    
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
		
		try {
			sessionService.validate(sessionToValidate);
			
			usersDao.remove(user);
			
		} catch (Exception e) {
			logger.error("Error occured during deleting", e);
		}
	}

	/**
     * {@inheritDoc}
     */
	@Override
	@Transactional
	public User addUser(String username, String sessionId, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.addUser starts");
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		return usersDao.save(user);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	@Transactional
	public User updateUser(String username, String sessionId, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.updateUser starts");
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		return usersDao.saveOrUpdate(user);
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
		
		User creator = usersDao.findByName(username);
		Date now = new Date();
		List<UserRole> entities = new ArrayList<UserRole>();
		
		for(Role r : rolesToAdd) {
			UserRole e = new UserRole();
			e.setCreatedAt(now);
			e.setCreator(creator);
			e.setRole(r);
			e.setUser(user);
			entities.add(e);
		}
		
		userRolesDao.saveOrUpdate(entities);
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
		
		List<UserRole> entities = userRolesDao.findByUserAndRole(user, rolesToDelete);
		userRolesDao.remove(entities);
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
		
		User creator = usersDao.findByName(username);
		Date now = new Date();
		List<UserStore> entities = new ArrayList<UserStore>();
		
		for(Store s : storesToAdd) {
			UserStore e = new UserStore();
			e.setCreatedAt(now);
			e.setCreator(creator);
			e.setStore(s);
			e.setUser(user);
			
			entities.add(e);
		}
		
		userStoresDao.saveOrUpdate(entities);
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
		
		List<UserStore> entities = userStoresDao.findByUserAndStore(user, storeToDelete);
		userStoresDao.remove(entities);
	}
	
}
