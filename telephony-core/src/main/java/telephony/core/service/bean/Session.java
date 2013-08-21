package telephony.core.service.bean;

import java.util.Date;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class Session {

    /**
     * asd.
     */
    private String username;

    /**
     * asd.
     */
    private String sessionId;
    
    /**
     * foo.
     */
    private Date validity;

    /**
     * asd.
     */
    public Session() {

    }
    
    /**
     * a.
     * @param username d.
     * @param sessionId d.
     * @return s.
     */
    public static Session create(String username, String sessionId) {
    	Session sess = new Session();
    	sess.setSessionId(sessionId);
    	sess.setUsername(username);
    	sess.setValidity(null);
    	
    	return sess;
    }

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     */
    public Session(final String username, final String sessionId, final Date validity) {
        this.username = username;
        this.sessionId = sessionId;
        this.validity = validity;
    }


	/**
     * asd.
     * @return asd.
     */
    public final String getUsername() {
        return username;
    }

    /**
     * asd.
     * @param usernamE asd.
     */
    public final void setUsername(final String usernamE) {
        this.username = usernamE;
    }

    /**
     * asd.
     * @return asd.
     */
    public final String getSessionId() {
        return sessionId;
    }

    /**
     * asd.
     * @param sessionID asd.
     */
    public final void setSessionId(final String sessionID) {
        this.sessionId = sessionID;
    }

    /**
     * asd.
     * @param o asd.
     * @return asd.
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Session session = (Session) o;

        if (!sessionId.equals(session.sessionId)) {
            return false;
        }
        if (!username.equals(session.username)) {
            return false;
        }
        
        if (!validity.equals(session.getValidity())) {
        	return false;
        }

        return true;
    }


    /**
     * asd.
     * @return asd.
     */
    @Override
    public final int hashCode() {
        int result = username.hashCode();
        result = 31 * result + sessionId.hashCode();
        result = 31 * result + validity.hashCode();
        return result;
    }


    /**
     * asd.
     * @return asd.
     */
	public Date getValidity() {
		return validity;
	}

	/**
	 * asd.
	 * @param validity asd.
	 */
	public void setValidity(Date validity) {
		this.validity = validity;
	}
}
