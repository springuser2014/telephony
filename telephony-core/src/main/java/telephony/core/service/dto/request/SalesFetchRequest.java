package telephony.core.service.dto.request;

import telephony.core.query.filter.SaleFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class SalesFetchRequest extends AuthRequest {

    SaleFilterCriteria filters;

    public SalesFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public SaleFilterCriteria getFilters() {
        return filters;
    }

    public void setFilters(SaleFilterCriteria filters) {
        this.filters = filters;
    }
}
