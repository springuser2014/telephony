package telephony.core.service.dto.response;

import java.util.List;

import telephony.core.service.dto.ProductFetchDto;

public class ProductFetchResponse extends BasicResponse {
	
	private List<ProductFetchDto> products;

	public List<ProductFetchDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductFetchDto> products) {
		this.products = products;
	}
}
