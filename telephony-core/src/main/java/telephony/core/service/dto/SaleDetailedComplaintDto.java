package telephony.core.service.dto;

public class SaleDetailedComplaintDto extends DetailedComplaintDto {
    private SaleDto sale;

    public SaleDetailedComplaintDto() {
        super();
    }

    public void setSale(SaleDto dto) {
        this.sale = dto;
    }

    public SaleDto getSale() {
        return sale;
    }
}
