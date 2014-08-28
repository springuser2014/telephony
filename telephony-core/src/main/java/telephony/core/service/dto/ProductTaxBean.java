package telephony.core.service.dto;

import java.util.Date;

public class ProductTaxBean {

	private Double rate;
	private Date taxFrom;
	private Date taxTo;
	
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Date getTaxFrom() {
		return taxFrom;
	}
	public void setTaxFrom(Date taxFrom) {
		this.taxFrom = taxFrom;
	}
	public Date getTaxTo() {
		return taxTo;
	}
	public void setTaxTo(Date taxTo) {
		this.taxTo = taxTo;
	}
}
