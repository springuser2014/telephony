package telephony.core.service.dto.request;

import telephony.core.query.filter.ModelFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class ModelFetchRequest extends AuthRequest {

    ModelFilterCriteria filters;

    public ModelFetchRequest() {
        super();
    }

    public ModelFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public ModelFilterCriteria getFilters() {
        return filters;
    }

    public void setFilters(ModelFilterCriteria filters) {
        this.filters = filters;
    }
}
