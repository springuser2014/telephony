package telephony.core.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.User;
import telephony.core.service.SessionService;
import telephony.core.service.dto.Session;
import telephony.core.util.StringGenerator;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

/**
 * Implementation of basic SessionService functionalities.
 */
public class SessionServiceImpl
extends AbstractBasicService 
implements SessionService {   
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private UsersDao usersDao;
	
	private StringGenerator generator;
	
	private Integer sessionValidity; 	
    
	//	TODO : remove?    
	@Inject
    @Override
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
    
	//	TODO : remove?    
	@Inject
    @Override
    public void setStringGenerator(StringGenerator stringGenerator) {
    	this.generator = stringGenerator;
    }
	
	//	TODO : remove?
    public Integer getSessionValidity() {
		
    	return sessionValidity;
	}
    
    //	TODO : remove?
    @Inject 
    public void setSessionValidity(@Named("sessionValidity") Integer sessionValidity) {
    	
    	this.sessionValidity = sessionValidity;
    }

	@Transactional
    public Session init(final String username, final String password) {
    	
    	logger.info("SessionServiceImpl.init starts");

        User u;

        try {
	        u = usersDao.findByNameAndPassword(username, password);
	
	        String sessionId = generator.nextSessionId();
	
	        u.setSessionId(sessionId);
	        u.setSessionValidity(new Date(new Date().getTime() + getSessionValidity()));
	
	        usersDao.saveOrUpdate(u);
	        
        } catch (Exception e) {
        	
        	logger.info("Error occured during session initialization", e);
            return null;
        } 
        
        Session session = new Session(u.getEmail(), u.getSessionId(), u.getSessionValidity());
        return session;
    }

	@Transactional
    public Session refresh(Session sessionToRefresh) {
    	
    	logger.debug("SessionServiceImpl.refresh starts");
    	
        User u;

        try {

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
	
	        // TODO : refactor
        } catch (Exception e) {
        	logger.error("Error occured during session refreshing", e);
            return null;
        } 

        Session session = new Session(u.getEmail(), u.getSessionId(), u.getSessionValidity());
        return session;
    }

	@Transactional
    public boolean destroy(Session sessionToDelete) {
    	
    	logger.debug("SessionServiceImpl.destroy starts");

        User u;
        try {

            u = usersDao.findByNameAndSessionId(
				sessionToDelete.getUsername(),
				sessionToDelete.getSessionId()
            );

            Date expiredDate = new Date(new Date().getTime() - getSessionValidity());
            u.setSessionValidity(expiredDate);
            usersDao.saveOrUpdate(u);

        } catch (Exception e) {
        	logger.warn("Error occured during session destroying", e);
            return false;
        } 

        return true;
    }

    @Transactional
	public boolean validate(Session sessionToValidate) {
		
		logger.debug("SessionServiceImpl.validate starts");
		
		User u;
		
		try {
			u = usersDao.findByNameAndSessionId(
					sessionToValidate.getUsername(), 
					sessionToValidate.getSessionId()
			);
			
			Date now = new Date();
			if (u.getSessionValidity().before(now) || 
					u.getSessionValidity().getTime() == now.getTime()) {
				return false;
			}			
			
		} catch (Exception e)  {
			logger.warn("Error occured during session validation", e);
			return false;
		} 
		
		return true;
	}

	@Override
	public long count(Session session) {
		
		return usersDao.count();
	}	
}
