package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class StoreDeleteRequest extends AuthRequest {

    Long storeId;

    public StoreDeleteRequest() {
        super();
    }

    public StoreDeleteRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
