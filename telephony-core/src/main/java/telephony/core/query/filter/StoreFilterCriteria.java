package telephony.core.query.filter;

/**
 * ad.
 */
public class StoreFilterCriteria extends
		AbstractFilterCriteria<StoreFilterCriteria> {

	private String label;
	private Integer minNumberOfProducts;
	private Integer maxNumberOfProducts;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getMinNumberOfProducts() {
		return minNumberOfProducts;
	}

	public void setMinNumberOfProducts(Integer minNumberOfProducts) {
		this.minNumberOfProducts = minNumberOfProducts;
	}

	public Integer getMaxNumberOfProducts() {
		return maxNumberOfProducts;
	}

	public void setMaxNumberOfProducts(Integer maxNumberOfProducts) {
		this.maxNumberOfProducts = maxNumberOfProducts;
	}

}
