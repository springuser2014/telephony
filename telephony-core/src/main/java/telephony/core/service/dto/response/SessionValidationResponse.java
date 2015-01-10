package telephony.core.service.dto.response;

public class SessionValidationResponse extends BasicResponse {

    Boolean isValid;

    public Boolean isValid() {
        return isValid;
    }

    public void setValid(Boolean isValid) {
        this.isValid = isValid;
    }
}
