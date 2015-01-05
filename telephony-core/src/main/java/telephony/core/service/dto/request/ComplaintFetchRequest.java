package telephony.core.service.dto.request;

import telephony.core.query.filter.ComplaintFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class ComplaintFetchRequest extends AuthRequest {

    ComplaintFilterCriteria filters;

    public ComplaintFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public ComplaintFilterCriteria getFilters() {
        return filters;
    }

    public void setFilters(ComplaintFilterCriteria filters) {
        this.filters = filters;
    }
}
