package telephony.core.query.filter;

public class TaxFilterCriteria extends AbstractFilterCriteria<TaxFilterCriteria> {

	private String taxDateStart;
	private String taxDateEnd;
	
	private Double rateFrom;
	private Double rateTo;
	
	public String getTaxDateStart() {
		return taxDateStart;
	}
	
	public void setTaxDateStart(String taxDateStart) {
		this.taxDateStart = taxDateStart;
	}
	
	public String getTaxDateEnd() {
		return taxDateEnd;
	}
	
	public void setTaxDateEnd(String taxDateEnd) {
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
}
