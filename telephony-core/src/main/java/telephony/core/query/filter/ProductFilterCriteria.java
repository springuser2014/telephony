package telephony.core.query.filter;

import telephony.core.entity.jpa.ProductStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductFilterCriteria
extends AbstractFilterCriteria<ProductFilterCriteria> {

	private List<Long> productIds;
	private List<Long> ignoreIds;
	private String imei;
	private String producer;
	private String model;
	private String color;
	private Long storeId;
	private Date deliveryDateStart;
	private Date deliveryDateEnd;
	private Date saleDateStart;
	private Date saleDateEnd;
	private ProductStatus status;
	private List<Long> salesIds;
	private List<Long> deliveriesIds;

	public ProductFilterCriteria() {
		this.productIds = new ArrayList<>();
		this.ignoreIds = new ArrayList<>();
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

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

	public List<Long> getProductIds() {
		return this.productIds;
	}

	public void addProductId(Long productId) {

		if (!productIds.contains(productId)) {
			productIds.add(productId);
		}
	}

	public void removeProductId(Long productId) {

		if (productIds.contains(productId)) {
			productIds.remove(productId);
		}
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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

	public List<Long> getSalesIds() {
		return salesIds;
	}

	public void setSalesIds(List<Long> salesIds) {
		this.salesIds = salesIds;
	}

	public void addSaleId(Long id) {

		if (!salesIds.contains(id)) {
			salesIds.add(id);
		}
	}

	public void removeSaleId(Long id) {

		if (salesIds.contains(id)) {
			salesIds.remove(id);
		}
	}

	public List<Long> getDeliveriesIds() {
		return deliveriesIds;
	}

	public void setDeliveriesIds(List<Long> deliveriesIds) {
		this.deliveriesIds = deliveriesIds;
	}

	public void addDeliveryId(Long id) {

		if (!deliveriesIds.contains(id)) {
			deliveriesIds.add(id);
		}
	}

	public void removeDeliveryId(Long id) {

		if (deliveriesIds.contains(id)) {
			deliveriesIds.remove(id);
		}
	}

	public void setDeliveryDateEnd(Date deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
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

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}


}
