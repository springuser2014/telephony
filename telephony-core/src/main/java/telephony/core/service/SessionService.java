package telephony.core.service;

import telephony.core.service.bean.Session;

/**
 * Describes basic operations on telephony's session service.
 *
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SessionService {

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
     * @param sessionToDelete TODO
     * @return asd.
     */
    boolean destroy(Session sessionToDelete);
    
    /**
     * asd.
     * @param sessionToDelete TODO
     * @return asd.
     */
    boolean validate(Session sessionToValidate);

}
