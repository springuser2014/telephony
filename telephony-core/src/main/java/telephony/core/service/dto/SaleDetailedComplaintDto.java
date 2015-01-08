package telephony.core.service.dto;

import telephony.core.service.DetailedComplaintDto;

public class SaleDetailedComplaintDto extends DetailedComplaintDto {
    private Long saleId;

    public SaleDetailedComplaintDto() {
        super();
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getSaleId() {
        return saleId;
    }
}
