package telephony.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UserStoresDao;
import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.User;
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
 * Implementation of basic UserService functionalities.
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
			//getEntityManager().getTransaction().begin();
			
			sessionService.validate(sessionToValidate);
			
			usersDao.remove(user);
			
			
		} catch (Exception e) {
			logger.error("Error occured during deleting", e);
		} finally {
			//getEntityManager().getTransaction().commit();
		}
	}

	/**
     * {@inheritDoc}
     */
	@Override
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
	public User updateUser(String username, String sessionId, User user)
			throws SessionServiceException, UserServiceException {
		
		logger.debug("UserServiceImpl.updateUser starts");
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		return usersDao.saveOrUpdate(user);
	}

	/**
	 * asd.
	 */
	@Override
	public long count() {
		
		return usersDao.count();
	}

	/**
	 * asd.
	 */
	@Override
	public User findByName(String username) {
		logger.debug("UserServiceImpl.findByName starts");
		
		return usersDao.findByName(username);
	}
}
