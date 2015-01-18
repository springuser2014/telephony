package telephony.core.service.dto.response;

import telephony.core.service.dto.ProductFetchDto;

public class ProductDetailsResponse extends BasicResponse {

    ProductFetchDto product;

    public void setProduct(ProductFetchDto product) {
        this.product = product;
    }

    public ProductFetchDto getProduct() {
        return product;
    }
}
