package telephony.core.service.converter;

import telephony.core.entity.jpa.Producer;
import telephony.core.service.dto.ProducerDto;

public final class ProducerConverter {

    public static ProducerDto toProducerDto(Producer producer) {
        ProducerDto dto = new ProducerDto();
        dto.setLabel(producer.getLabel());
        dto.setId(producer.getId());

        return dto;
    }


}
