package telephony.core.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import telephony.core.dao.UsersDao;
import telephony.core.service.bean.Session;
import telephony.core.util.StringGenerator;

/**
 * Describes basic operations on telephony's session service.
 */
public interface SessionService {
	
	/**
	 * Gives current value of session validity property.
     * @return Session validity time in miliseconds.
	 */
	Integer getSessionValidity();
	
	/**
	 * Sets value of session validity property (in miliseconds).
	 * @param sessionValidity foo.
	 */
	@Inject
	void setSessionValidity(@Named("sessionValidity") Integer sessionValidity);
	
	/**
	 * Sets UsersDao object.
	 * @param usersDao foo.
	 */
	void setUsersDao(UsersDao usersDao);
	
	/**
	 * Sets StringGenerator object.
	 * @param stringGenerator foo.
	 */
	@Inject
	void setStringGenerator(StringGenerator stringGenerator);

    /**
     * Initializes user's session.
     * Looks for user with given name and password then tries to
     * initialize session. Session's validity time is defined 
     * by {@link #setSessionValidity(Integer)}.
     * @param username asd.
     * @param password asd.
     * @return session object if initialized successfully otherwise null.
     */
    Session init(String username, String password);

    /**
     * Refreshes user's session. 
     * Session's life will be increased by value from {@link #getSessionValidity()}.
     * @param sessionToRefresh User's existing session to refresh.
     * @return Returns renewed session's object or the old one if expired.
     */
    Session refresh(Session sessionToRefresh);

    /**
     * Tries to destroy a valid user's session.
     * @param sessionToDelete User's session.
     * @return if successfully destroyed true otherwise false.
     */
    boolean destroy(Session sessionToDelete);
    
    /**
     * Validates user session.
     * @param sessionToValidate User's session.
     * @return True if session is valid otherwise false.
     */
    boolean validate(Session sessionToValidate);

}
