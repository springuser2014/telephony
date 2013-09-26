package telephony.core.service;

import java.util.Date;

import telephony.core.entity.jpa.ProductStatus;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class ProductQueryCriteria {
	private String imei;
	
	private String producer;
	private String model;
	private String color;
	private Long storeId;
	private Date deliveryDateStart;
	private Date deliveryDateEnd;
	private ProductStatus status;

	/**
	 * asd.
	 * @param imei asd.
	 * @param producer asd. 
	 * @param model asd.
	 * @param color asd.
	 * @param storeId asd. 
	 * @param deliveryDateStart asd.
	 * @param deliveryDateEnd asd.
	 * @param status as.
	 */
	public ProductQueryCriteria(String imei, String producer, String model,
			String color, Long storeId, Date deliveryDateStart,
			Date deliveryDateEnd, ProductStatus status) {
		this.imei = imei;
		this.producer = producer;
		this.model = model;
		this.color = color;
		this.storeId = storeId;
		this.deliveryDateStart = deliveryDateStart;
		this.deliveryDateEnd = deliveryDateEnd;
		this.status = status;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * asd.
	 * @param imei asd.
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * asd.
	 * @return asd.
	 */
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