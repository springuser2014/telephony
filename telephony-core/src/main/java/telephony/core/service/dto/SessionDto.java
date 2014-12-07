package telephony.core.service.dto;

import java.util.Date;

/**
 * asd.
 */
public class SessionDto {

    private String username;
    private String sessionId;
    private Date validity;

    /**
     * asd.
     */
    public SessionDto() {

    }
    
    /**
     * asd.
     * @return a.
     */
    public static SessionDto create() {
    	return new SessionDto();
    }
    
    /**
     * a.
     * @param username d.
     * @param sessionId d.
     * @return s.
     */
    public static SessionDto create(String username, String sessionId) {
    	SessionDto sess = new SessionDto();
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
    public SessionDto(final String username, final String sessionId, final Date validity) {
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
    public final void setUsername(final String username) {
        this.username = username;
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
    public final void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
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
	public SessionDto setValidity(Date validity) {
		this.validity = validity;
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

        SessionDto session = (SessionDto) o;

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

}
