package telephony.core.service.dto;

import telephony.core.query.filter.ProductFilterCriteria;

public class ProductFetchRequest {

	private String username;
	private String sessionId;
	private ProductFilterCriteria filtersCriteria;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public ProductFilterCriteria getFiltersCriteria() {
		return filtersCriteria;
	}

	public void setFiltersCriteria(ProductFilterCriteria filtersCriteria) {
		this.filtersCriteria = filtersCriteria;
	}
	
}
