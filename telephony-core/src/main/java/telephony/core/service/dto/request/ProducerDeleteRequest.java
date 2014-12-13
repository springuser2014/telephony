package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ProducerDeleteRequest extends AuthRequest {

    Long producerId;

    public ProducerDeleteRequest() {
        super();
    }

    public ProducerDeleteRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }
}
