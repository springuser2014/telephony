package telephony.core.service.dto;

import telephony.core.service.DetailedComplaintDto;

public class ProductDetailedComplaintDto extends DetailedComplaintDto {
    private ProductAddDto product;

    public ProductDetailedComplaintDto() {
        super();
    }

    public void setProduct(ProductAddDto product) {
        this.product = product;
    }

    public ProductAddDto getProduct() {
        return product;
    }
}
