package telephony.core.service.dto.request;

import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ModelEditDto;

public class ModelEditRequest extends AuthRequest {

    ModelDto modelDto;

    public ModelDto getModelDto() {
        return modelDto;
    }

    public void setModelDto(ModelDto modelDto) {
        this.modelDto = modelDto;
    }
}
