package telephony.core.service.dto;

public class ProductComplaintEditDto extends ComplaintEditDto {

    Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
