package telephony.ws.resource.products;

import telephony.core.service.dto.ProductDetailsRequestDto;
import telephony.core.service.dto.ProductDetailsResponseDto;

public interface ProductsDetailsResource {

	String URL = "/products/details";
	
	ProductDetailsResponseDto details(ProductDetailsRequestDto request);
}
