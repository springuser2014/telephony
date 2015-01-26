package telephony.core.query.filter;

public abstract class AbstractFilterCriteria<C extends AbstractFilterCriteria<C>> {
	
	private Integer page;
	private Integer perPage;
	
	public Integer getPage() {
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public C setPage(Integer page) {
		this.page = page;
		return (C) this;
	}
	
	public Integer getPerPage() {
		return perPage;
	}
	
	@SuppressWarnings("unchecked")
	public C setPerPage(Integer perPage) {
		this.perPage = perPage;
		return (C) this;
	}	
}
