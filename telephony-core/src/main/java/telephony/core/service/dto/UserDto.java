package telephony.core.service.dto;

import java.util.Date;

public class UserDto {

    Long id;
    String email;
    String password;
    Boolean isActive;
    String sessionId;
    Date sessionValidity;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getSessionValidity() {
        return sessionValidity;
    }

    public void setSessionValidity(Date sessionValidity) {
        this.sessionValidity = sessionValidity;
    }
}
