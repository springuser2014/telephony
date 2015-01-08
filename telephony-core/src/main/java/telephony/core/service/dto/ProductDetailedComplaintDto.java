package telephony.core.service.dto;

import telephony.core.service.DetailedComplaintDto;

public class ProductDetailedComplaintDto extends DetailedComplaintDto {
    private ProductDto product;

    public ProductDetailedComplaintDto() {
        super();
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public ProductDto getProduct() {
        return product;
    }
}
