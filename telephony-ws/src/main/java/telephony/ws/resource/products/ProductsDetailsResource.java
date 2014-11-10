package telephony.ws.resource.products;

import telephony.core.service.dto.request.ProductDetailsRequestDto;
import telephony.core.service.dto.response.ProductDetailsResponseDto;

public interface ProductsDetailsResource {

	String URL = "/products/details";
	
	ProductDetailsResponseDto details(ProductDetailsRequestDto request);
}
