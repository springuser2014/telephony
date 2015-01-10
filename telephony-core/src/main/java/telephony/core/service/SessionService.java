package telephony.core.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import telephony.core.dao.UsersDao;
import telephony.core.service.dto.request.SessionDetailsRequest;
import telephony.core.service.dto.response.SessionDetailsResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;
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
     * @throws telephony.core.service.exception.SessionServiceException asd.
     * @return session object if initialized successfully otherwise null.
     */
    SessionDto init(String username, String password) throws SessionServiceException;

    /**
     * Refreshes user's session. 
     * Session's life will be increased by value from {@link #getSessionValidity()}.
     * @param sessionToRefresh User's existing session to refresh.
     * @throws SessionServiceException asd.
     * @return Returns renewed session's object or the old one if expired.
     */
    SessionDto refresh(SessionDto sessionToRefresh) throws SessionServiceException;

    /**
     * Tries to destroy a valid user's session.
     * @param sessionToDelete User's session.
     * @throws SessionServiceException d.
     * @return if successfully destroyed true otherwise false.
     */
    boolean destroy(SessionDto sessionToDelete) throws SessionServiceException;
    
    /**
     * Validates user session.
     * @param sessionToValidate User's session.
     * @throws SessionServiceException sd.
     * @return True if session is valid otherwise false.
     */
    boolean validate(SessionDto sessionToValidate) throws SessionServiceException;

	SessionDetailsResponse fetchDetails(SessionDetailsRequest request) throws SessionServiceException;
}
