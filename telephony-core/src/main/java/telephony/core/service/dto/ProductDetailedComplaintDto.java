package telephony.core.service.dto;

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
