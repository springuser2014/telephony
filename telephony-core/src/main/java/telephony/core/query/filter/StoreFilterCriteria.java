package telephony.core.query.filter;

/**
 * ad.
 */
public class StoreFilterCriteria 
extends AbstractFilterCriteria<StoreFilterCriteria> {

	private String label;
	private Integer minNumberOfProducts;
	private Integer maxNumberOfProducts;
	
	/**
	 * asd.
	 * @return a.
	 */
	public static StoreFilterCriteria create() {
		return new StoreFilterCriteria();
	}

	/**
	 * asd.
	 * @return a.
	 */
	public String label() {
		return label;
	}

	/**
	 * asd.
	 * @param label a.
	 * @return a.
	 */
	public StoreFilterCriteria label(String label) {
		this.label = label;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Integer minNumberOfProducts() {
		return minNumberOfProducts;
	}

	/**
	 * asd.
	 * @param minNumberOfProducts a.
	 * @return a.
	 */
	public StoreFilterCriteria minNumberOfProducts(Integer minNumberOfProducts) {
		this.minNumberOfProducts = minNumberOfProducts;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Integer maxNumberOfProducts() {
		return maxNumberOfProducts;
	}

	/**
	 * asd.
	 * @param maxNumberOfProducts asd.
	 * @return a.
	 */
	public StoreFilterCriteria maxNumberOfProducts(Integer maxNumberOfProducts) {
		this.maxNumberOfProducts = maxNumberOfProducts;
		return this;
	}
}
