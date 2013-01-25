package telephony.core.service;

public class Session {

    public String username;
    public String sessionId;

    public Session() {}

    public Session(String username, String sessionId) {
        this.username = username;
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (!sessionId.equals(session.sessionId)) return false;
        if (!username.equals(session.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + sessionId.hashCode();
        return result;
    }
}
