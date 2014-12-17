package telephony.core.service.dto.request;

import telephony.core.query.filter.StoreFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class StoreFetchRequest extends AuthRequest {

    StoreFilterCriteria filters;

    public StoreFetchRequest(SessionDto session) {
        super(session);
    }

    public StoreFilterCriteria getFilters() {
        return filters;
    }

    public void setFilters(StoreFilterCriteria filters) {
        this.filters = filters;
    }
}
