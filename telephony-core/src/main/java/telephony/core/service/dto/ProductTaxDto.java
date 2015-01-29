package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductTaxDto extends ProductTaxAddDto {

	private Date to;

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
}
