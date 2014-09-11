package telephony.core.service.dto;

import java.util.Date;

/**
 * asd.
 */
public class Session {

    private String username;

    private String sessionId;

    private Date validity;

    /**
     * asd.
     */
    public Session() {

    }
    
    /**
     * asd.
     * @return a.
     */
    public static Session create() {
    	return new Session();
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
     * @param validity asd.
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
     * @param username asd.
     * @return asd.
     */
    public final Session setUsername(final String username) {
        this.username = username;
        return this;
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
     * @param sessionId asd.
     * @return asd.
     */
    public final Session setSessionId(final String sessionId) {
        this.sessionId = sessionId;
        return this;
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
	 * @return asd.
	 */
	public Session setValidity(Date validity) {
		this.validity = validity;
		return this;
	}
}
