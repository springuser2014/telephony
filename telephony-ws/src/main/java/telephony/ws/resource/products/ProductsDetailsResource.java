package telephony.ws.resource.products;

import telephony.core.service.dto.request.ProductDetailsRequest;
import telephony.core.service.dto.response.ProductDetailsResponse;

public interface ProductsDetailsResource {

	String URL = "/products/details";
	
	ProductDetailsResponse details(ProductDetailsRequest request);
}
