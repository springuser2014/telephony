package telephony.core.query.filter;

import java.util.Date;
import java.util.List;

public class ModelFilterCriteriaBuilder extends
        ModelFilterCriteriaBuilderBase<ModelFilterCriteriaBuilder> {
    public static ModelFilterCriteriaBuilder modelFilterCriteria() {
        return new ModelFilterCriteriaBuilder();
    }

    public ModelFilterCriteriaBuilder() {
        super(new ModelFilterCriteria());
    }

    public ModelFilterCriteria build() {
        return getInstance();
    }
}

class ModelFilterCriteriaBuilderBase<GeneratorT extends ModelFilterCriteriaBuilderBase<GeneratorT>> {
    private ModelFilterCriteria instance;

    protected ModelFilterCriteriaBuilderBase(ModelFilterCriteria aInstance) {
        instance = aInstance;
    }

    protected ModelFilterCriteria getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withProducerId(Long aValue) {
        instance.setProducerId(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withProducer(String producer) {
        instance.setProducer(producer);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withModelId(Long aValue) {
        instance.addModelId(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withModelIds(List<Long> aValues) {
        instance.getModelIds().addAll(aValues);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withLabel(String aValue) {
        instance.setLabel(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withPage(Integer aValue) {
        instance.setPage(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withPerPage(Integer aValue) {
        instance.setPerPage(aValue);

        return (GeneratorT) this;
    }
}
