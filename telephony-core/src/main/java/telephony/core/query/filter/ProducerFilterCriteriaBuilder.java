package telephony.core.query.filter;

import java.util.Collection;
import java.util.List;

public class ProducerFilterCriteriaBuilder extends
        ProducerFilterCriteriaBuilderBase<ProducerFilterCriteriaBuilder> {

    public static ProducerFilterCriteriaBuilder producerFilterCriteria() { return new ProducerFilterCriteriaBuilder(); }

    public ProducerFilterCriteriaBuilder() { super(new ProducerFilterCriteria()); }

    public ProducerFilterCriteria build() { return getInstance(); }
}

class ProducerFilterCriteriaBuilderBase<GeneratorT extends ProducerFilterCriteriaBuilderBase<GeneratorT>> {
    private ProducerFilterCriteria instance;

    protected ProducerFilterCriteriaBuilderBase(ProducerFilterCriteria aInstance) {
        instance = aInstance;
    }

    protected ProducerFilterCriteria getInstance() {
        return instance;
    }

    public GeneratorT withLabel(String aValue) {
        instance.setLabel(aValue);

        return (GeneratorT) this;
    }

    public GeneratorT withProducerId(Long producerId) {
        instance.addProducerId(producerId);

        return (GeneratorT) this;
    }

    public GeneratorT withProducerIds(Collection<Long> producerIds) {
        instance.getProducerIds().addAll(producerIds);

        return (GeneratorT) this;
    }

    public GeneratorT withPage(Integer page) {
        instance.setPage(page);

        return (GeneratorT) this;
    }

    public GeneratorT withPerPage(Integer perPage) {
        instance.setPerPage(perPage);

        return (GeneratorT) this;
    }

}
