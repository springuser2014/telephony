package telephony.core.query.filter;

import telephony.core.entity.jpa.ProductStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * asd.
 */
public class ProductFilterCriteria extends
		AbstractFilterCriteria<ProductFilterCriteria> {

	private List<Long> productIds;
	private String imei;
	private String producer;
	private String model;
	private String color;
	private Long storeId;
	private Date deliveryDateStart;
	private Date deliveryDateEnd;
	private ProductStatus status;

	public ProductFilterCriteria() {
		this.productIds = new ArrayList<Long>();
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

	public void setDeliveryDateEnd(Date deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}
}
