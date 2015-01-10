package telephony.core.service.dto.response;

public class SessionDestroyResponse extends BasicResponse {

    Boolean isDestroyed;

    public Boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(Boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }
}
