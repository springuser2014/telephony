package telephony.core.service.dto.response;

import telephony.core.service.dto.ModelDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModelFetchResponse extends BasicResponse {

    List<ModelDto> models;

    public ModelFetchResponse() {
        this.models = new ArrayList<ModelDto>();
    }

    public List<ModelDto> getModels() {
        return models;
    }

    public void setModels(List<ModelDto> models) {
        this.models = models;
    }

    public void addModel(ModelDto modelDto) {

        if (!this.models.contains(modelDto)) {
            this.models.add(modelDto);
        }
    }

    public void removeModel(ModelDto modelDto) {

        if (this.models.contains(modelDto)) {
            this.models.remove(modelDto);
        }
    }
}
