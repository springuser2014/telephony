package telephony.core.query.filter;

import java.util.Date;

public class SaleFilterCriteria
extends AbstractFilterCriteria<SaleFilterCriteria> {

	private Date saleDateStart;
	private Date saleDateEnd;

	private Double sumFrom;
	private Double sumTo;

	private Long soldBy;
	
	private String label;
	private String labelLike;

	private Integer minNumberOfProducts;
	private Integer maxNumberOfProducts;

	private Long contactId;
	private Long storeId;

	public Long getSoldBy() {
		return soldBy;
	}

	public void setSoldBy(Long soldBy) {
		this.soldBy = soldBy;
	}
	
	public Date getSaleDateStart() {
		return saleDateStart;
	}

	public void setSaleDateStart(Date saleDateStart) {
		this.saleDateStart = saleDateStart;
	}

	public Date getSaleDateEnd() {
		return saleDateEnd;
	}

	public void setSaleDateEnd(Date saleDateEnd) {
		this.saleDateEnd = saleDateEnd;
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

	public String getLabelLike() {
		return labelLike;
	}

	public void setLabelLike(String labelLike) {
		this.labelLike = labelLike;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
}
