package telephony.core.service.dto.request;

import telephony.core.service.dto.ProductEditDto;
import telephony.core.service.dto.SessionDto;

public class ProductEditRequest extends AuthRequest {

    ProductEditDto productDto;

    public ProductEditRequest() {
        super();
    }

    public ProductEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public ProductEditDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductEditDto productDto) {
        this.productDto = productDto;
    }
}
