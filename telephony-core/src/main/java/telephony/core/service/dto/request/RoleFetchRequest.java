package telephony.core.service.dto.request;

import telephony.core.query.filter.RoleFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class RoleFetchRequest extends AuthRequest {

    private RoleFilterCriteria filters;

    public RoleFetchRequest() {
        super();
    }

    public RoleFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public void setFilters(RoleFilterCriteria filters) {
        this.filters = filters;
    }

    public RoleFilterCriteria getFilters() {
        return filters;
    }
}
