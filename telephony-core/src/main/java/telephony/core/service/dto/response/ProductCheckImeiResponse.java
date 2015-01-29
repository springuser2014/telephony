package telephony.core.service.dto.response;

public class ProductCheckImeiResponse extends BasicResponse {

    private Boolean isAvailable;

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}

