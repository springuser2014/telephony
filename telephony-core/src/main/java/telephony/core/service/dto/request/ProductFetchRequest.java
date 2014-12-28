package telephony.core.service.dto.request;

import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.service.dto.SessionDto;

public class ProductFetchRequest extends AuthRequest {

	private ProductFilterCriteria filters;

	public ProductFetchRequest() {
		super();
	}

	public ProductFetchRequest(SessionDto sessionDto) {
		super(sessionDto);
	}
	
	public ProductFilterCriteria getFilters() {
		return filters;
	}

	public void setFilters(ProductFilterCriteria filtersCriteria) {
		this.filters = filtersCriteria;
	}
	
}
