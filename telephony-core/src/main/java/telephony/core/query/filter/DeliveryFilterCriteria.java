package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO : refactor , create builder

public class DeliveryFilterCriteria
extends AbstractFilterCriteria<DeliveryFilterCriteria> {

	private List<Long> ignoreIds;
	private List<Long> deliveriesIds;

	private Date deliveryDateStart;
	private Date deliveryDateEnd;

	private Double sumFrom;
	private Double sumTo;

	private Long deliveredBy;
	private Long registeredBy;

	private String label;
	private Integer minNumberOfProducts;
	private Integer maxNumberOfProducts;

	private Long storeId;
	private Long contactId;

	public DeliveryFilterCriteria() {
		this.ignoreIds = new ArrayList<>();
		this.deliveriesIds = new ArrayList<>();
	}

	public List<Long> getDeliveriesIds() {
		return deliveriesIds;
	}

	public void setDeliveriesIds(List<Long> deliveriesIds) {
		this.deliveriesIds = deliveriesIds;
	}

	public void addDeliveryId(Long deliveryId) {

		if (!deliveriesIds.contains(deliveryId)) {
			deliveriesIds.add(deliveryId);
		}
	}

	public void removeDeliveryId(Long deliveryId) {

		if (deliveriesIds.contains(deliveryId)) {
			deliveriesIds.remove(deliveryId);
		}
	}

	public List<Long> getIgnoreIds() {
		return ignoreIds;
	}

	public void setIgnoreIds(List<Long> ignoreIds) {
		this.ignoreIds = ignoreIds;
	}

	public void addIgnoreId(Long productId) {

		if (!ignoreIds.contains(productId)) {
			ignoreIds.add(productId);
		}
	}

	public void removeIgnoreId(Long productId) {

		if (ignoreIds.contains(productId)) {
			ignoreIds.remove(productId);
		}
	}


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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
}
