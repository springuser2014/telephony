package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaxFilterCriteria
extends AbstractFilterCriteria<TaxFilterCriteria> {

	private List<Long> taxIds;
	private Date taxDateStart;
	private Date taxDateEnd;
	
	private Double rateFrom;
	private Double rateTo;

	public TaxFilterCriteria() {
		this.taxIds = new ArrayList<Long>();
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
}
