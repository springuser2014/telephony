package telephony.core.query.filter;

/**
 * asd.
 * @param <C> asd.
 */
public abstract class AbstractFilterCriteria<C extends AbstractFilterCriteria<C>> {
	
	private Integer page;
	private Integer perPage;
	
	/**
	 * asd.
	 * @return a.
	 */
	public Integer getPage() {
		return page;
	}
	
	/**
	 * asd.
	 * @param page a. 
	 * @return a.
	 */
	@SuppressWarnings("unchecked")
	public C setPage(Integer page) {
		this.page = page;
		return (C) this;
	}
	
	/**
	 * asd.
	 * @return a.
	 */
	public Integer getPerPage() {
		return perPage;
	}
	
	/**
	 * aa.
	 * @param perPage d.
	 * @return d.
	 */
	@SuppressWarnings("unchecked")
	public C setPerPage(Integer perPage) {
		this.perPage = perPage;
		return (C) this;
	}	
}
