package telephony.core.service.impl;

import java.util.Date;

import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.User;
import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
import telephony.core.util.StringGenerator;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Implementation of basic SessionService functionalities.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SessionServiceImpl
    extends AbstractBasicService implements SessionService {   

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
            return null;
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

        } catch (Exception e) {
            return null;
        }

        Session session = new Session(u.getEmail(), u.getSessionId(), u.getSessionValidity());
        return session;
    }

    /**
     * {@inheritDoc}
     */
    public final boolean destroy(Session sessionToDelete) {

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
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
	public boolean validate(Session sessionToValidate) {
		User u;
		
		try {
			getEntityManager().getTransaction().begin();
			
			u = usersDao.findByNameAndSessionId(
					sessionToValidate.getUsername(), 
					sessionToValidate.getSessionId()
			);
			
			Date now = new Date();
			if (u.getSessionValidity().before(now) || u.getSessionValidity().equals(now)) {
				return false;
			}
			
			getEntityManager().getTransaction().commit();
		} catch (Exception e)  {
			return false;
		}
		
		return true;
	}	
}
