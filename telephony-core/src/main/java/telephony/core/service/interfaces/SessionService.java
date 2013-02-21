package telephony.core.service.interfaces;

import telephony.core.service.bean.Session;

/**
 * asd.
 *
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SessionService {

    /**
     * asd.
     * @param username asd.
     * @param password asd.
     * @return asd.
     */
    Session init(String username, String password);

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @return asd.
     */
    Session refresh(String username, String sessionId);

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @return asd.
     */
    boolean destroy(String username, String sessionId);

}
