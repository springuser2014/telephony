package telephony.core.query.filter;

import telephony.core.entity.jpa.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * ad.
 */
public class StoreFilterCriteria extends
		AbstractFilterCriteria<StoreFilterCriteria> {

	private List<Long> storeIds;
	private String label;
	private Integer minNumberOfProducts;
	private Integer maxNumberOfProducts;

	public StoreFilterCriteria() {
		this.storeIds = new ArrayList<Long>();
	}

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

	public List<Long> getStoreIds() {
		return storeIds;
	}

	public void addStoreId(Long storeId) {

		if (!this.storeIds.contains(storeId)) {
			this.storeIds.add(storeId);
		}
	}

	public void removeStoreId(Long storeId) {

		if (this.storeIds.contains(storeId)) {
			this.storeIds.remove(storeId);
		}
	}
}
