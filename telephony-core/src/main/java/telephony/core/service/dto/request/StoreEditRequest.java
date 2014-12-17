package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.StoreDto;

public class StoreEditRequest extends AuthRequest {

    StoreDto storeDto;

    public StoreEditRequest() {
        super();
    }

    public StoreEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public StoreDto getStoreDto() {
        return storeDto;
    }

    public void setStoreDto(StoreDto storeDto) {
        this.storeDto = storeDto;
    }
}
