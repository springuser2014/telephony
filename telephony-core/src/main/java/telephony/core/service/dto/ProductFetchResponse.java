package telephony.core.service.dto;

import java.util.List;

public class ProductFetchResponse  {
	
	private List<ProductSearchBean> products;

	public List<ProductSearchBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProductSearchBean> products) {
		this.products = products;
	}
	
	
}
