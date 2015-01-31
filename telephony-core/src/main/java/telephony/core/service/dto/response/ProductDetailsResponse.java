package telephony.core.service.dto.response;

import telephony.core.service.dto.ProductDetailsDto;

public class ProductDetailsResponse extends BasicResponse {

    ProductDetailsDto product;

    public void setProduct(ProductDetailsDto product) {
        this.product = product;
    }

    public ProductDetailsDto getProduct() {
        return product;
    }
}
