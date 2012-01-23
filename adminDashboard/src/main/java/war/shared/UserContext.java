package war.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.HashMap;

public class UserContext implements IsSerializable {

    protected String username;

    protected HashMap<String, String> privileges;
    protected String sessionId;

    public UserContext() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
