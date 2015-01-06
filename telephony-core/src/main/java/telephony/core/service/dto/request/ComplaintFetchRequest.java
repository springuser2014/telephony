package telephony.core.service.dto.request;

import telephony.core.query.filter.ComplaintFilterCriteria;
import telephony.core.service.dto.SessionDto;

public abstract class ComplaintFetchRequest<F extends ComplaintFilterCriteria> extends AuthRequest {

    F filters;

    public F getFilters() {
        return filters;
    }

    public void setFilters(F filters) {
        this.filters = filters;
    }

    public ComplaintFetchRequest(SessionDto dto) {
        super(dto);
    }

}
