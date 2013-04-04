package telephony.core.service.bean;

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
     * asd.
     */
    public Session() {

    }


    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     */
    public Session(final String username, final String sessionId) {
        this.username = username;
        this.sessionId = sessionId;
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
        return result;
    }
}
