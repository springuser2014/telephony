package telephony.core.query.filter;

import java.util.Date;

import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.User;

/**
 * asd.
 */
public class DeliveryFilterCriteria 
extends AbstractFilterCriteria<DeliveryFilterCriteria> {

	private Date deliveryDateStart;
	private Date deliveryDateEnd;
	
	private Double sumFrom;
	private Double sumTo;
	
	private Contact deliveredBy;
	private User registeredBy;
	
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
		return deliveryDateStart;
	}

	/**
	 * asd.
	 * @param deliveryDateStart a.
	 * @return asd.
	 */
	public DeliveryFilterCriteria deliveryDateStart(Date deliveryDateStart) {
		this.deliveryDateStart = deliveryDateStart;
		return this;
	}

	/**
	 * ads.
	 * @return a.
	 */
	public Date deliveryDateEnd() {
		return deliveryDateEnd;
	}

	/**
	 * asd.
	 * @param deliveryDateEnd a.
	 * @return a.
	 */
	public DeliveryFilterCriteria deliveryDateEnd(Date deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
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
	public Contact deliveredBy() {
		return deliveredBy;
	}

	/**
	 * asd.
	 * @param deliveredBy asd.
	 * @return asd.
	 */
	public DeliveryFilterCriteria deliveredBy(Contact deliveredBy) {
		deliveredBy = deliveredBy;
		return this;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public User registeredBy() {
		return registeredBy;
	}

	/**
	 * asd.
	 * @param registeredBy asd.
	 * @return asd.
	 */
	public DeliveryFilterCriteria registeredBy(User registeredBy) {
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
}
