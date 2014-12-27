package telephony.core.service.converter;

import telephony.core.entity.jpa.Model;
import telephony.core.service.dto.ModelDto;

public class ModelConverter {

    public ModelDto toModelDto(Model model) {

        ModelDto dto = new ModelDto();
        dto.setId(model.getId());
        dto.setLabel(model.getLabel());
        dto.setProducer(model.getProducer().getLabel());

        return dto;
    }
}
