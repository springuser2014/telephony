package telephony.core.service.dto;

import java.util.Date;

public class ProductTaxDto extends ProductTaxAddDto {

	private Date to;

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
}
