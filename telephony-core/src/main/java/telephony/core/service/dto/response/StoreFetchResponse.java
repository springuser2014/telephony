package telephony.core.service.dto.response;

import telephony.core.service.dto.StoreSearchDto;

import java.util.ArrayList;
import java.util.List;

public class StoreFetchResponse extends BasicResponse {

    List<StoreSearchDto> stores;

    public StoreFetchResponse() {
        this.stores = new ArrayList<>();
    }

    public List<StoreSearchDto> getStores() {
        return stores;
    }

    public void addStore(StoreSearchDto dto) {

        if (!this.stores.contains(dto)) {
            this.stores.add(dto);
        }
    }

    public void removeStore(StoreSearchDto dto) {

        if (this.stores.contains(dto)) {
            this.stores.remove(dto);
        }
    }

    public void setStores(List<StoreSearchDto> stores) {
        this.stores = stores;
    }
}
