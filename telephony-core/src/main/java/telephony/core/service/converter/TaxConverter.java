package telephony.core.service.converter;

import telephony.core.entity.jpa.Tax;
import telephony.core.service.dto.TaxDto;

public class TaxConverter {

    public Tax toEntity(TaxDto dto) {

        Tax entity = new Tax();
        entity.setFrom(dto.getFrom());
        entity.setTo(dto.getTo());
        entity.setRate(dto.getRate());

        return entity;
    }

    public void updateEntity(TaxDto dto, Tax entity) {

        entity.setFrom(dto.getFrom());
        entity.setTo(dto.getTo());
        entity.setRate(dto.getRate());
    }

    public TaxDto toDto(Tax entity) {

        TaxDto dto = new TaxDto();

        dto.setId(entity.getId());
        dto.setFrom(entity.getFrom());
        dto.setTo(entity.getTo());
        dto.setRate(entity.getRate());

        return dto;
    }
}
