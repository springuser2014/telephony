package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.List;

public class ModelFilterCriteria extends AbstractFilterCriteria<ModelFilterCriteria> {

    List<Long> modelIds;
    String label;

    String producer;
    Long producerId;

    public ModelFilterCriteria() {
        modelIds = new ArrayList<Long>();
    }

    public void addModelId(Long modelId) {

        if (!this.modelIds.contains(modelId)) {
            this.modelIds.add(modelId);
        }
    }

    public void removeModelId(Long modelId) {

        if (this.modelIds.contains(modelId)) {
            this.modelIds.remove(modelId);
        }
    }

    public List<Long> getModelIds() {
        return this.modelIds;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }
}
