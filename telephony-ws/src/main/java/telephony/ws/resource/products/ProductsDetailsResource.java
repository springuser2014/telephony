package telephony.ws.resource.products;

import telephony.core.service.dto.ProductDetailsRequest;
import telephony.core.service.dto.ProductDetailsResponse;

public interface ProductsDetailsResource {

	String URL = "/products/details";
	
	ProductDetailsResponse details(ProductDetailsRequest request);
}
