package telephony.core.service.dto.request;

import telephony.core.query.filter.ProductFilterCriteria;

public class ProductFetchRequestDto extends AuthRequestDto {

	private ProductFilterCriteria filtersCriteria;
	
	public ProductFilterCriteria getFiltersCriteria() {
		return filtersCriteria;
	}

	public void setFiltersCriteria(ProductFilterCriteria filtersCriteria) {
		this.filtersCriteria = filtersCriteria;
	}
	
}
