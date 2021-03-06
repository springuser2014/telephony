package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaxFilterCriteria
extends AbstractFilterCriteria<TaxFilterCriteria> {

	private List<Long> ignoreIds;
	private List<Long> taxIds;
	private Date taxDateStart;
	private Date taxDateEnd;
	
	private Double rateFrom;
	private Double rateTo;

	private Boolean currentlyActive;
	private Date activeAt;

	public TaxFilterCriteria() {
		taxIds = new ArrayList<>();
		ignoreIds = new ArrayList<>();
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

	public Date getTaxDateStart() {
		return taxDateStart;
	}

	public void setTaxDateStart(Date taxDateStart) {
		this.taxDateStart = taxDateStart;
	}

	public Date getTaxDateEnd() {
		return taxDateEnd;
	}

	public void setTaxDateEnd(Date taxDateEnd) {
		this.taxDateEnd = taxDateEnd;
	}

	public Double getRateFrom() {
		return rateFrom;
	}
	
	public void setRateFrom(Double rateFrom) {
		this.rateFrom = rateFrom;
	}
	
	public Double getRateTo() {
		return rateTo;
	}
	
	public void setRateTo(Double rateTo) {
		this.rateTo = rateTo;
	}

	public void setTaxIds(List<Long> taxIds) {
		this.taxIds = taxIds;
	}

	public List<Long> getTaxIds() {
		return taxIds;
	}

	public void addTaxId(Long taxId) {

		if (!this.taxIds.contains(taxId)) {
			this.taxIds.add(taxId);
		}
	}

	public void removeTaxId(Long taxId) {

		if (this.taxIds.contains(taxId)) {
			this.taxIds.remove(taxId);
		}
	}

	public Boolean getCurrentlyActive() {
		return currentlyActive;
	}

	public void setCurrentlyActive(Boolean currentlyActive) {
		this.currentlyActive = currentlyActive;
	}

	public Date getActiveAt() {
		return activeAt;
	}

	public void setActiveAt(Date activeAt) {
		this.activeAt = activeAt;
	}
}
