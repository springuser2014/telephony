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
	public Integer page() {
		return page;
	}
	
	/**
	 * asd.
	 * @param page a. 
	 * @return a.
	 */
	@SuppressWarnings("unchecked")
	public C page(Integer page) {
		this.page = page;
		return (C) this;
	}
	
	/**
	 * asd.
	 * @return a.
	 */
	public Integer perPage() {
		return perPage;
	}
	
	/**
	 * aa.
	 * @param perPage d.
	 * @return d.
	 */
	@SuppressWarnings("unchecked")
	public C perPage(Integer perPage) {
		this.perPage = perPage;
		return (C) this;
	}
}
