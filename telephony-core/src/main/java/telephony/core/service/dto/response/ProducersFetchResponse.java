package telephony.core.service.dto.response;

import telephony.core.service.dto.ProducerDto;

import java.util.List;

public class ProducersFetchResponse extends BasicResponse {

    List<ProducerDto> producers;

    public List<ProducerDto> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducerDto> producers) {
        this.producers = producers;
    }
}
