package telephony.core.service.dto.request;

import telephony.core.query.filter.ModelFilterCriteria;

public class ModelFetchRequest extends AuthRequest {

    ModelFilterCriteria filters;

    public ModelFilterCriteria getFilters() {
        return filters;
    }

    public void setFilters(ModelFilterCriteria filters) {
        this.filters = filters;
    }
}
