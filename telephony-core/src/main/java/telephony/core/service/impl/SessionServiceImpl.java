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
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SessionServiceImpl
    extends AbstractBasicService implements SessionService {   

	private UsersDao usersDao;    
	private StringGenerator generator;

	private Integer sessionValidity;    
    
	@Inject
    @Override
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
    
	@Inject
    @Override
    public void setStringGenerator(StringGenerator stringGenerator) {
    	this.generator = stringGenerator;
    }

    /**
     * asd.
     * @param username asd.
     * @param password asd.
     * @return asd.
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
	
	        //usersDao.saveOrUpdate(u);
	        usersDao.save(u);
	
	        getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            return null;
        }

        Session session = new Session(u.getEmail(), u.getSessionId());
        return session;
    }

    public Integer getSessionValidity() {
		
    	return sessionValidity;
	}
    
    @Inject 
    public void setSessionValidity(@Named("sessionValidity") Integer sessionValidity) {
    	
    	this.sessionValidity = sessionValidity;
    }

	/**
     * asd.
     * @param sessionToRefresh asd.
     * @return asd.
     */
    public Session refresh(Session sessionToRefresh) {
        User u;

        try {
	        getEntityManager().getTransaction().begin();
	
	        u = usersDao.findByNameAndSessionId(
	        		sessionToRefresh.getUsername(), 
	        		sessionToRefresh.getSessionId()
	        );
	        
	        // TODO introduce constant
	        Date expirationDate =
	             new Date(u.getSessionValidity().getTime() + getSessionValidity());
	        Date today = new Date();
	
	        if (expirationDate.after(today)) {
	
	            u.setSessionValidity(today);
	            usersDao.saveOrUpdate(u);
	        }
	
	        getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            return null;
        }

        Session session = new Session(u.getEmail(), u.getSessionId());
        return session;
    }

    /**
     * asd.
     * @param sessionToDelete asd.
     * @return asd.
     */
    public final boolean destroy(Session sessionToDelete) {

        User u;
        try {
            getEntityManager().getTransaction().begin();

            u = usersDao.findByNameAndSessionId(
				sessionToDelete.getUsername(),
				sessionToDelete.getSessionId()
            );

            // TODO : constant!
            Date expiredDate = new Date(1000);
            u.setSessionValidity(expiredDate);
            usersDao.saveOrUpdate(u);

            getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * asd.
     * @param sessionToValidate asd.
     * @return asd.
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
