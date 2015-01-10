package telephony.core.service.dto.request;

public abstract class BasicRequest {

    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
