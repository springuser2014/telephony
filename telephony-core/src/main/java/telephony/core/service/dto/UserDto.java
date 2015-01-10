package telephony.core.service.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class UserDto {

    Long id;
    String email;
    Boolean isActive;
    String sessionId;
    Date sessionValidity;

    public UserDto() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
