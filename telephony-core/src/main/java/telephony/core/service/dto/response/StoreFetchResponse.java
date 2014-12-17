package telephony.core.service.dto.response;

import telephony.core.service.dto.StoreDto;

import java.util.ArrayList;
import java.util.List;

public class StoreFetchResponse extends BasicResponse {

    List<StoreDto> stores;

    public StoreFetchResponse() {
        this.stores = new ArrayList<StoreDto>();
    }

    public List<StoreDto> getStores() {
        return stores;
    }

    public void addStore(StoreDto dto) {

        if (!this.stores.contains(dto)) {
            this.stores.add(dto);
        }
    }

    public void removeStore(StoreDto dto) {

        if (this.stores.contains(dto)) {
            this.stores.remove(dto);
        }
    }

    public void setStores(List<StoreDto> stores) {
        this.stores = stores;
    }
}
