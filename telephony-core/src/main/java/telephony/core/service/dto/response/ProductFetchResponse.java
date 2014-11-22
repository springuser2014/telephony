package telephony.core.service.dto.response;

import java.util.List;

import telephony.core.service.dto.ProductSearchDto;

public class ProductFetchResponse extends BasicResponse {
	
	private List<ProductSearchDto> products;

	public List<ProductSearchDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductSearchDto> products) {
		this.products = products;
	}
}
