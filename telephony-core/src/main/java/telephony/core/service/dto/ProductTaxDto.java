package telephony.core.service.dto;

import java.util.Date;

public class ProductTaxDto {

	private Long id;
	private Date taxFrom;
	private Date taxTo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
