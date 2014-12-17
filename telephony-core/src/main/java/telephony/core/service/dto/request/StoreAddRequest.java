package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.StoreDto;

public class StoreAddRequest extends AuthRequest {

    StoreDto store;

    public StoreAddRequest() {
        super();
    }

    public StoreAddRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public StoreDto getStoreDto() {
        return store;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }
}
