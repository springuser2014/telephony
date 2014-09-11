package telephony.core.query.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import telephony.core.entity.jpa.ProductStatus;

/**
 * asd.
 */
public class ProductFilterCriteria 
extends AbstractFilterCriteria<ProductFilterCriteria> {

	private String imei;	
	private String producer;
	private String model;
	private String color;
	private Long storeId;
	private String deliveryDateStart;
	private String deliveryDateEnd;
	private ProductStatus status;
	
	/**
	 * asd.
	 * @return asd.
	 */
	public static ProductFilterCriteria create() {
		return new ProductFilterCriteria();
	}
	
	/**
	 * asd.
	 */
	public ProductFilterCriteria() {
		
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
	public ProductFilterCriteria(String imei, String producer, String model,
			String color, Long storeId, Date deliveryDateStart,
			Date deliveryDateEnd, ProductStatus status) {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const

		this.imei = imei;
		this.producer = producer;
		this.model = model;
		this.color = color;
		this.storeId = storeId;
		this.deliveryDateStart = SDF.format(deliveryDateStart);
		this.deliveryDateEnd = SDF.format(deliveryDateEnd);
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
	public ProductFilterCriteria setImei(String imei) {
		this.imei = imei;
		return this;
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
	public ProductFilterCriteria setProducer(String producer) {
		this.producer = producer;
		return this;
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
	public ProductFilterCriteria setModel(String model) {
		this.model = model;
		return this;
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
	public ProductFilterCriteria setColor(String color) {
		this.color = color;
		return this;
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
	public ProductFilterCriteria setStoreId(Long storeId) {
		this.storeId = storeId;
		return this;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Date getDeliveryDateStart() {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const
		if (this.deliveryDateStart != null) {
			try {
				return SDF.parse(deliveryDateStart);
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * asd.
	 * @param deliveryDateStart asd.
	 */
	public ProductFilterCriteria setDeliveryDateStart(Date deliveryDateStart) {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const
		if (deliveryDateStart != null) {
			this.deliveryDateStart = SDF.format(deliveryDateStart);
		} else {
			this.deliveryDateStart = null;	
		}
		
		return this;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Date getDeliveryDateEnd() {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const

		if (this.deliveryDateEnd != null) {
			try {
				return SDF.parse(deliveryDateEnd);
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * qwdas.
	 * @param deliveryDateEnd asd.
	 */
	public ProductFilterCriteria setDeliveryDateEnd(Date deliveryDateEnd) {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const

		if (deliveryDateEnd != null) {
			this.deliveryDateStart = SDF.format(deliveryDateEnd);
		} else {
			this.deliveryDateStart = null;	
		}
		
		return this;
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
	public ProductFilterCriteria setStatus(ProductStatus status) {
		this.status = status;
		return this;
	}
}
