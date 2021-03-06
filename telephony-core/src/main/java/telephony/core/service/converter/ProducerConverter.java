package telephony.core.service.converter;

import telephony.core.entity.jpa.Producer;
import telephony.core.service.dto.ProducerDto;

public class ProducerConverter {

    public ProducerDto toProducerDto(Producer producer) {
        ProducerDto dto = new ProducerDto();
        dto.setLabel(producer.getLabel());
        dto.setProducerId(producer.getId());

        return dto;
    }


}
