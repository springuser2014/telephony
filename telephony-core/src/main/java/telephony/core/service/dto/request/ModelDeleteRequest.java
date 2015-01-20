package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ModelDeleteRequest extends AuthRequest {

    Long modelId;

    public ModelDeleteRequest() {
        super();
    }

    public ModelDeleteRequest(SessionDto session) {
        super(session);
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
}
