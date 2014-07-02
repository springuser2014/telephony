package telephony.core.service;

import java.util.Date;

import telephony.core.entity.jpa.ProductStatus;

/**
 * asd.
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
	 */
	public ProductQueryCriteria() {
		
	}

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

	/**
	 * asd.
	 * @param producer asd.
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * asd.
	 * @param model asd.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * asd.
	 * @param color a.
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * asd.
	 * @param storeId as.
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Date getDeliveryDateStart() {
		return deliveryDateStart;
	}

	/**
	 * asd.
	 * @param deliveryDateStart asd.
	 */
	public void setDeliveryDateStart(Date deliveryDateStart) {
		this.deliveryDateStart = deliveryDateStart;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Date getDeliveryDateEnd() {
		return deliveryDateEnd;
	}

	/**
	 * qwdas.
	 * @param deliveryDateEnd asd.
	 */
	public void setDeliveryDateEnd(Date deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public ProductStatus getStatus() {
		return status;
	}

	/**
	 * qwe.
	 * @param status asd.
	 */
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
}