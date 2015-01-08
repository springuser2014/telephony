package telephony.core.service.dto;

import telephony.core.service.DetailedComplaintDto;

public class ProductDetailedComplaintDto extends DetailedComplaintDto {
    private Long productId;

    public ProductDetailedComplaintDto() {
        super();
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
