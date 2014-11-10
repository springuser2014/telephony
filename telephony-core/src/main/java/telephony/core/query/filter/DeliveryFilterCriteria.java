package telephony.core.query.filter;

import java.util.Date;

// TODO : refactor , create builder
/**
 * asd.
 */
public class DeliveryFilterCriteria extends
		AbstractFilterCriteria<DeliveryFilterCriteria> {

	private Date deliveryDateStart;
	private Date deliveryDateEnd;

	private Double sumFrom;
	private Double sumTo;

	private Long deliveredBy;
	private Long registeredBy;

	private String label;
	private Integer minNumberOfProducts;
	private Integer maxNumberOfProducts;

	public Date getDeliveryDateStart() {
		return deliveryDateStart;
	}

	public void setDeliveryDateStart(Date deliveryDateStart) {
		this.deliveryDateStart = deliveryDateStart;
	}

	public Date getDeliveryDateEnd() {
		return deliveryDateEnd;
	}

	public void setDeliveryDateEnd(Date deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
	}

	public Double getSumFrom() {
		return sumFrom;
	}

	public void setSumFrom(Double sumFrom) {
		this.sumFrom = sumFrom;
	}

	public Double getSumTo() {
		return sumTo;
	}

	public void setSumTo(Double sumTo) {
		this.sumTo = sumTo;
	}

	public Long getDeliveredBy() {
		return deliveredBy;
	}

	public void setDeliveredBy(Long deliveredBy) {
		this.deliveredBy = deliveredBy;
	}

	public Long getRegisteredBy() {
		return registeredBy;
	}

	public void setRegisteredBy(Long registeredBy) {
		this.registeredBy = registeredBy;
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

}
