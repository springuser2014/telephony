package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.List;

public class StoreFilterCriteria
extends AbstractFilterCriteria<StoreFilterCriteria> {

	private List<Long> ignoreIds;
	private List<Long> storeIds;
	private String label;
	private Integer minNumberOfProducts;
	private Integer maxNumberOfProducts;

	public StoreFilterCriteria() {
		this.storeIds = new ArrayList<>();
		this.ignoreIds = new ArrayList<>();
	}

	public void addIgnoreId(Long id) {

		if (!ignoreIds.contains(id)) {
			ignoreIds.add(id);
		}
	}

	public void removeIgnoreId(Long id) {

		if (ignoreIds.contains(id)) {
			ignoreIds.remove(id);
		}
	}

	public List<Long> getIgnoreIds() {
		return ignoreIds;
	}

	public void setIgnoreIds(List<Long> ignoreIds) {
		this.ignoreIds = ignoreIds;
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

	public void setStoreIds(List<Long> storeIds) {
		this.storeIds = storeIds;
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
