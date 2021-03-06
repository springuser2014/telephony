package telephony.core.service.dto.request;

import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ModelEditDto;
import telephony.core.service.dto.SessionDto;

public class ModelEditRequest extends AuthRequest {

    // TODO : add collection for batch edition
    ModelDto modelDto;

    public ModelEditRequest() {
        super();
    }

    public ModelEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public ModelDto getModelDto() {
        return modelDto;
    }

    public void setModelDto(ModelDto modelDto) {
        this.modelDto = modelDto;
    }
}
