package telephony.core.query.filter;


import java.util.Date;

public class SaleComplaintFilterCriteriaBuilder extends
        SaleComplaintFilterCriteriaBuilderBase<SaleComplaintFilterCriteriaBuilder> {
    public static SaleComplaintFilterCriteriaBuilder saleComplaintFilterCriteria() {
        return new SaleComplaintFilterCriteriaBuilder();
    }

    public SaleComplaintFilterCriteriaBuilder() {
        super(new SaleComplaintFilterCriteria());
    }

    public SaleComplaintFilterCriteria build() {
        return getInstance();
    }
}

class SaleComplaintFilterCriteriaBuilderBase<GeneratorT extends SaleComplaintFilterCriteriaBuilderBase<GeneratorT>> {
    private SaleComplaintFilterCriteria instance;

    protected SaleComplaintFilterCriteriaBuilderBase(
            SaleComplaintFilterCriteria aInstance) {
        instance = aInstance;
    }

    protected SaleComplaintFilterCriteria getInstance() {
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
    public GeneratorT withSaleId(Long saleId) {
        instance.addSaleId(saleId);

        return (GeneratorT) this;
    }
}
