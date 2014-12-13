package telephony.core.service.dto.request;

import telephony.core.query.filter.ProducerFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class ProducersFetchRequest extends AuthRequest {

    ProducerFilterCriteria filters;

    public ProducersFetchRequest() {
        super();
        this.filters = new ProducerFilterCriteria();
    }

    public ProducersFetchRequest(SessionDto sessionDto) {
        super(sessionDto);
        this.filters = new ProducerFilterCriteria();
    }

    public ProducerFilterCriteria getFilters() {
        return filters;
    }

    public void setFilters(ProducerFilterCriteria filters) {
        this.filters = filters;
    }
}
