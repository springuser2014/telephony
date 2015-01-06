package telephony.core.service.dto;

public class SaleComplaintDto extends ComplaintDto {

    Long saleId;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
