package telephony.core.service.dto;

public class SaleComplaintEditDto extends ComplaintEditDto {

    Long saleId;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
