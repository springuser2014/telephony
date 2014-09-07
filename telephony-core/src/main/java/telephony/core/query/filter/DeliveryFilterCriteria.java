package telephony.core.query.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.User;

/**
 * asd.
 */
public class DeliveryFilterCriteria 
extends AbstractFilterCriteria<DeliveryFilterCriteria> {

	private String deliveryDateStart;
	private String deliveryDateEnd;
	
	private Double sumFrom;
	private Double sumTo;
	
	private Long deliveredBy;
	private Long registeredBy;
	
	private String label;
	private Integer minNumberOfProducts;
	private Integer maxNumberOfProducts;
	
	/**
	 * asd.
	 * @return as.
	 */
	public static DeliveryFilterCriteria create() {
		return new DeliveryFilterCriteria();
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Date deliveryDateStart() {
		if (this.deliveryDateStart != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const
			try {
				return sdf.parse(deliveryDateStart);
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}	
	}

	/**
	 * asd.
	 * @param deliveryDateStart a.
	 * @return asd.
	 */
	public DeliveryFilterCriteria deliveryDateStart(Date deliveryDateStart) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const
		this.deliveryDateStart = sdf.format(deliveryDateStart);
		return this;
	}

	/**
	 * ads.
	 * @return a.
	 */
	public Date deliveryDateEnd() {
		if (this.deliveryDateEnd != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const
			try {
				return sdf.parse(deliveryDateEnd);
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * asd.
	 * @param deliveryDateEnd a.
	 * @return a.
	 */
	public DeliveryFilterCriteria deliveryDateEnd(Date deliveryDateEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const
		this.deliveryDateEnd = sdf.format(deliveryDateEnd);
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Double sumFrom() {
		return sumFrom;
	}

	/**
	 * asd.
	 * @param sumFrom a.
	 * @return asd.
	 */
	public DeliveryFilterCriteria sumFrom(Double sumFrom) {
		this.sumFrom = sumFrom;
		return this;
	}

	/**
	 * asd.
	 * @return ads.
	 */
	public Double sumTo() {
		return sumTo;
	}

	/**
	 * asd.
	 * @param sumTo asd.
	 * @return ads.
	 */
	public DeliveryFilterCriteria sumTo(Double sumTo) {
		this.sumTo = sumTo;
		
		return this;
	}

	/**
	 * asd.
	 * @return ads.
	 */
	public Long deliveredBy() {
		return deliveredBy;
	}

	/**
	 * asd.
	 * @param deliveredBy asd.
	 * @return asd.
	 */
	public DeliveryFilterCriteria deliveredBy(Long deliveredBy) {
		this.deliveredBy = deliveredBy;
		return this;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Long registeredBy() {
		return registeredBy;
	}

	/**
	 * asd.
	 * @param registeredBy asd.
	 * @return asd.
	 */
	public DeliveryFilterCriteria registeredBy(Long registeredBy) {
		this.registeredBy = registeredBy;
		return this;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String label() {
		return label;
	}

	/**
	 * asd.
	 * @param label asd.
	 * @return asd.
	 */
	public DeliveryFilterCriteria label(String label) {
		this.label = label;
		return this;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Integer minNumberOfProducts() {
		return minNumberOfProducts;
	}

	/**
	 * asd.
	 * @param minNumberOfProducts a.
	 * @return asd.
	 */
	public DeliveryFilterCriteria minNumberOfProducts(Integer minNumberOfProducts) {
		this.minNumberOfProducts = minNumberOfProducts;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Integer maxNumberOfProducts() {
		return maxNumberOfProducts;
	}

	/**
	 * asd.
	 * @param maxNumberOfProducts a.
	 * @return asd.
	 */
	public DeliveryFilterCriteria maxNumberOfProducts(Integer maxNumberOfProducts) {
		this.maxNumberOfProducts = maxNumberOfProducts;
		return this;
	}

	public String getDeliveryDateStart() {
		return deliveryDateStart;
	}

	public void setDeliveryDateStart(String deliveryDateStart) {
		this.deliveryDateStart = deliveryDateStart;
	}

	public String getDeliveryDateEnd() {
		return deliveryDateEnd;
	}

	public void setDeliveryDateEnd(String deliveryDateEnd) {
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
