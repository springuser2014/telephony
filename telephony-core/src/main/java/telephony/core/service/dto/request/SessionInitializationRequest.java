package telephony.core.service.dto.request;

public class SessionInitializationRequest extends BasicRequest {

    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
