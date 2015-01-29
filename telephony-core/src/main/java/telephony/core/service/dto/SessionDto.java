package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDto {

    private String username;
    private String sessionId;
    private Date validity;

    public SessionDto() {

    }
    
    public static SessionDto create() {
    	return new SessionDto();
    }
    
    public static SessionDto create(String username, String sessionId) {
    	SessionDto sess = new SessionDto();
    	sess.setSessionId(sessionId);
    	sess.setUsername(username);
    	sess.setValidity(null);
    	
    	return sess;
    }

    public static SessionDto create(String username, String sessionId, Date validity) {
        SessionDto sess = new SessionDto();
        sess.setSessionId(sessionId);
        sess.setUsername(username);
        sess.setValidity(validity);

        return sess;
    }

    public SessionDto(final String username, final String sessionId, final Date validity) {
        this.username = username;
        this.sessionId = sessionId;
        this.validity = validity;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(final String username) {
        this.username = username;
    }

    public final String getSessionId() {
        return sessionId;
    }

    public final void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getValidity() {
		return validity;
	}

	public SessionDto setValidity(Date validity) {
		this.validity = validity;
		return this;
	}

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

    @Override
    public final int hashCode() {
        int result = username.hashCode();
        result = 31 * result + sessionId.hashCode();
        result = 31 * result + validity.hashCode();
        return result;
    }

}
