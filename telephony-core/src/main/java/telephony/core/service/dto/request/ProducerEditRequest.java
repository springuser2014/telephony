package telephony.core.service.dto.request;

import telephony.core.query.filter.ProducerFilterCriteria;
import telephony.core.service.dto.ProducerDto;
import telephony.core.service.dto.SessionDto;

public class ProducerEditRequest extends AuthRequest {

    ProducerDto producerDto;

    public ProducerEditRequest() {
        super();
    }

    public ProducerEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public ProducerDto getProducerDto() {
        return producerDto;
    }

    public void setProducerDto(ProducerDto producerDto) {
        this.producerDto = producerDto;
    }
}
