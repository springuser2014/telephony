package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ProductDetailsRequest extends AuthRequest {

    Long productId;

    public ProductDetailsRequest() {
        super();
    }

    public ProductDetailsRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
