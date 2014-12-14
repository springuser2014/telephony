package telephony.core.service.dto.request;

import telephony.core.query.filter.TaxFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class TaxFetchRequest extends AuthRequest {

    TaxFilterCriteria filters;

    public TaxFetchRequest() {
        super();
        this.filters = new TaxFilterCriteria();
    }

    public TaxFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
        this.filters = new TaxFilterCriteria();
    }

    public TaxFilterCriteria getFilters() {
        return filters;
    }

    public void setFilters(TaxFilterCriteria filters) {
        this.filters = filters;
    }
}
