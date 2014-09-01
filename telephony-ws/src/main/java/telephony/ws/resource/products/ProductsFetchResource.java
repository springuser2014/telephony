package telephony.ws.resource.products;

import telephony.core.service.dto.ProductFetchRequest;
import telephony.core.service.dto.ProductFetchResponse;

public interface ProductsFetchResource {

	String URL = "/products/fetch";
	
	ProductFetchResponse fetch(ProductFetchRequest request);
}
