package telephony.core.service.dto.request;

import telephony.core.query.filter.ProductFilterCriteria;

public class ProductFetchRequest extends AuthRequest {

	private ProductFilterCriteria filtersCriteria;
	
	public ProductFilterCriteria getFiltersCriteria() {
		return filtersCriteria;
	}

	public void setFiltersCriteria(ProductFilterCriteria filtersCriteria) {
		this.filtersCriteria = filtersCriteria;
	}
	
}
