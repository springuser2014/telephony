package telephony.ws.resource.products;

import telephony.core.service.dto.request.ProductEditRequest;
import telephony.core.service.dto.response.ProductEditResponse;

public interface ProductEditResource {

    String URL = "/products/edit";

    ProductEditResponse edit(ProductEditRequest request);

}
