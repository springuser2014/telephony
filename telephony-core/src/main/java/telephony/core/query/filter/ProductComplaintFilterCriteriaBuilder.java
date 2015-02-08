package telephony.core.query.filter;


import telephony.core.entity.enumz.ComplaintStatus;

import java.util.Date;

public class ProductComplaintFilterCriteriaBuilder extends
        ProductComplaintFilterCriteriaBuilderBase<ProductComplaintFilterCriteriaBuilder> {
    public static ProductComplaintFilterCriteriaBuilder productComplaintFilterCriteria() {
        return new ProductComplaintFilterCriteriaBuilder();
    }

    public ProductComplaintFilterCriteriaBuilder() {
        super(new ProductComplaintFilterCriteria());
    }

    public ProductComplaintFilterCriteria build() {
        return getInstance();
    }
}

class ProductComplaintFilterCriteriaBuilderBase<GeneratorT extends ProductComplaintFilterCriteriaBuilderBase<GeneratorT>> {
    private ProductComplaintFilterCriteria instance;

    protected ProductComplaintFilterCriteriaBuilderBase(
            ProductComplaintFilterCriteria aInstance) {
        instance = aInstance;
    }

    protected ProductComplaintFilterCriteria getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withLabel(String aValue) {
        instance.setTitle(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withDescription(String aValue) {
        instance.setDescription(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withReportedDateFrom(Date aValue) {
        instance.setReportedDateFrom(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withReportedDateTo(Date aValue) {
        instance.setReportedDateTo(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withPhoneNumber(String aValue) {
        instance.setPhoneNumber(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withEmail(String aValue) {
        instance.setEmail(aValue);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withProductId(Long productId) {
        instance.addProductId(productId);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withStatus(ComplaintStatus status) {
        instance.setStatus(status);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withPage(Integer page) {
        instance.setPage(page);

        return (GeneratorT) this;
    }

    @SuppressWarnings("unchecked")
    public GeneratorT withPerPage(Integer pp) {
        instance.setPerPage(pp);

        return (GeneratorT) this;
    }

}
