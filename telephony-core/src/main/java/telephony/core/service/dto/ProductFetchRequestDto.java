package telephony.core.service.dto;

import telephony.core.query.filter.ProductFilterCriteria;

public class ProductFetchRequestDto extends AuthDto {

	private ProductFilterCriteria filtersCriteria;
	
	public ProductFilterCriteria getFiltersCriteria() {
		return filtersCriteria;
	}

	public void setFiltersCriteria(ProductFilterCriteria filtersCriteria) {
		this.filtersCriteria = filtersCriteria;
	}
	
}
