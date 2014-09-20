package telephony.core.service.dto;

import java.util.List;

public class ProductFetchResponseDto  {
	
	private List<ProductSearchDto> products;

	public List<ProductSearchDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductSearchDto> products) {
		this.products = products;
	}
}
