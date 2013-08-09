package telephony.core.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.User;
import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
import telephony.core.util.StringGenerator;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.UnitOfWork;

/**
 * Implementation of basic SessionService functionalities.
 */
public class SessionServiceImpl
    extends AbstractBasicService implements SessionService {   
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private UsersDao usersDao;
	
	private StringGenerator generator;
	
	private Integer sessionValidity; 	
    
	/**
	 * {@inheritDoc}
	 */
	@Inject
    @Override
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
    
	/**
	 * {@inheritDoc}
	 */
	@Inject
    @Override
    public void setStringGenerator(StringGenerator stringGenerator) {
    	this.generator = stringGenerator;
    }

    /**
     * {@inheritDoc}
     */
    public final Session init(
    		final String username, 
    		final String password) {
    	
    	logger.debug("SessionServiceImpl.init starts");

        User u;

        try {

	        getEntityManager().getTransaction().begin();
	
	        u = usersDao.findByNameAndPassword(username, password);
	
	        String sessionId = generator.nextSessionId();
	
	        u.setSessionId(sessionId);
	        u.setSessionValidity(new Date(new Date().getTime() + getSessionValidity()));
	
	        usersDao.saveOrUpdate(u);
	        
	        getEntityManager().getTransaction().commit();

        } catch (Exception e) {
        	
        	logger.error("Error occured during session initialization",e);
            return null;
        } finally {
        	if (getEntityManager().getTransaction().isActive()) {
        		getEntityManager().getTransaction().rollback();
        	}
        }

        Session session = new Session(u.getEmail(), u.getSessionId(), u.getSessionValidity());
        return session;
    }

    /**
     * {@inheritDoc}
     */
    public Integer getSessionValidity() {
		
    	return sessionValidity;
	}
    
    /**
     * {@inheritDoc}
     */
    @Inject 
    public void setSessionValidity(@Named("sessionValidity") Integer sessionValidity) {
    	
    	this.sessionValidity = sessionValidity;
    }

	/**
     * {@inheritDoc}
     */
    public Session refresh(Session sessionToRefresh) {
    	
    	logger.debug("SessionServiceImpl.refresh starts");
    	
        User u;

        try {
	        getEntityManager().getTransaction().begin();
	
	        u = usersDao.findByNameAndSessionId(
	        		sessionToRefresh.getUsername(), 
	        		sessionToRefresh.getSessionId()
	        );
	       
	        Date today = new Date();
	        Date expirationDate =
	             new Date(u.getSessionValidity().getTime() + getSessionValidity());
	       	
	        if (expirationDate.after(today)) {
	
	            u.setSessionValidity(new Date(today.getTime() + getSessionValidity()));
	            usersDao.saveOrUpdate(u);
	        }
	
	        getEntityManager().getTransaction().commit();
	        // TODO : refactor
        } catch (Exception e) {
        	logger.error("Error occured during session refreshing", e);
            return null;
        } finally {
        	if (getEntityManager().getTransaction().isActive()) {
        		getEntityManager().getTransaction().rollback();
        	}
        }

        Session session = new Session(u.getEmail(), u.getSessionId(), u.getSessionValidity());
        return session;
    }

    /**
     * {@inheritDoc}
     */
    public final boolean destroy(Session sessionToDelete) {
    	
    	logger.debug("SessionServiceImpl.destroy starts");

        User u;
        try {
            getEntityManager().getTransaction().begin();

            u = usersDao.findByNameAndSessionId(
				sessionToDelete.getUsername(),
				sessionToDelete.getSessionId()
            );

            Date expiredDate = new Date(new Date().getTime() - getSessionValidity());
            u.setSessionValidity(expiredDate);
            usersDao.saveOrUpdate(u);

            getEntityManager().getTransaction().commit();

        } catch (Exception e) {
        	logger.warn("Error occured during session destroying", e);
            return false;
        } finally {
        	if (getEntityManager().getTransaction().isActive()) {
        		getEntityManager().getTransaction().rollback();
        	}
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
	public boolean validate(Session sessionToValidate) {
		
		logger.debug("SessionServiceImpl.validate starts");
		
		User u;
		
		try {
			//getEntityManager().getTransaction().begin();
			
			u = usersDao.findByNameAndSessionId(
					sessionToValidate.getUsername(), 
					sessionToValidate.getSessionId()
			);
			
			Date now = new Date();
			if (u.getSessionValidity().before(now) || u.getSessionValidity().equals(now)) {
				return false;
			}			
			
		} catch (Exception e)  {
			logger.warn("Error occured during session validation", e);
			return false;
		} finally {
			
//			getEntityManager().getTransaction().commit();
//			
//        	if (getEntityManager().getTransaction().isActive()) {
//        		getEntityManager().getTransaction().rollback();
//        	}
        }
		
		return true;
	}

	@Override
	public long count() {
		
		return usersDao.count();
	}	
}
