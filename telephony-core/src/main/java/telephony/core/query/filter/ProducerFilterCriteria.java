package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.List;

public class ProducerFilterCriteria
extends AbstractFilterCriteria<ProducerFilterCriteria> {

    String label;
    List<Long> producerIds;

    public ProducerFilterCriteria() {
        this.producerIds = new ArrayList<Long>();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Long> getProducerIds() {
        return producerIds;
    }

    public void addProducerId(Long producerId) {
        if (!this.producerIds.contains(producerId)) {
            this.producerIds.add(producerId);
        }
    }

    public void removeProducerId(Long producerId) {

        if (this.producerIds.contains(producerId)) {
            this.producerIds.remove(producerId);
        }
    }

}
