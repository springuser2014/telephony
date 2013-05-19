package telephony.core.service;

import telephony.core.dao.UsersDao;
import telephony.core.service.bean.Session;
import telephony.core.util.StringGenerator;

/**
 * Describes basic operations on telephony's session service.
 *
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SessionService {
	
	/**
	 * asd.
	 * @return foo.
	 */
	Integer getSessionValidity();
	
	/**
	 * asd.
	 * @param sessionValidity foo.
	 */
	void setSessionValidity(Integer sessionValidity);
	
	/**
	 * TODO asd.
	 * @param usersDao foo.
	 */
	void setUsersDao(UsersDao usersDao);
	
	/**
	 * TODO asd.
	 * @param stringGenerator foo.
	 */
	void setStringGenerator(StringGenerator stringGenerator);

    /**
     * Tries to initialize user session using given data.  
     * @param username asd.
     * @param password asd.
     * @return asd.
     */
    Session init(String username, String password);

    /**
     * Tries to refresh user session.
     * @param sessionToRefresh asd.
     * @return asd.
     */
    Session refresh(Session sessionToRefresh);

    /**
     * Tries to invalidate given user session.
     * @param sessionToDelete User's session.
     * @return asd.
     */
    boolean destroy(Session sessionToDelete);
    
    /**
     * Validates user session.
     * @param sessionToValidate User's session.
     * @return True if session is valid otherwise false.
     */
    boolean validate(Session sessionToValidate);

}
