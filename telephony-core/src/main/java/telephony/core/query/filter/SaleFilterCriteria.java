package telephony.core.query.filter;

import java.util.Date;

/**
 * asd.
 */
public class SaleFilterCriteria
extends AbstractFilterCriteria<SaleFilterCriteria> {

	private Date deliveryDateStart;
	private Date deliveryDateEnd;
	
	private Double sumFrom;
	private Double sumTo;
	
	private Long deliveredBy;
	private Long registeredBy;
	
	private String label;
	private Integer minNumberOfProducts;
	private Integer maxNumberOfProducts;
	
	/**
	 * asd.
	 * @return a.
	 */
	public static SaleFilterCriteria create() {
		return new SaleFilterCriteria();
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
	 * @return a.
	 */
	public SaleFilterCriteria deliveryDateStart(Date deliveryDateStart) {
		this.deliveryDateStart = deliveryDateStart;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Date deliveryDateEnd() {
		return deliveryDateEnd;
	}

	/**
	 * asd.
	 * @param deliveryDateEnd a.
	 * @return ad.
	 */
	public SaleFilterCriteria deliveryDateEnd(Date deliveryDateEnd) {
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
	 * @return a.
	 */
	public SaleFilterCriteria sumFrom(Double sumFrom) {
		this.sumFrom = sumFrom;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Double sumTo() {
		return sumTo;
	}

	/**
	 * asd.
	 * @param sumTo a.
	 * @return asd.
	 */
	public SaleFilterCriteria sumTo(Double sumTo) {
		this.sumTo = sumTo;
		return this;
	}

	/**
	 * ads.
	 * @return a.
	 */
	public Long deliveredBy() {
		return deliveredBy;
	}

	/**
	 * asd.
	 * @param deliveredBy a.
	 * @return ad.
	 */
	public SaleFilterCriteria deliveredBy(Long deliveredBy) {
		this.deliveredBy = deliveredBy;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Long registeredBy() {
		return registeredBy;
	}

	/**
	 * asd.
	 * @param registeredBy a.
	 * @return ad.
	 */
	public SaleFilterCriteria registeredBy(Long registeredBy) {
		this.registeredBy = registeredBy;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public String label() {
		return label;
	}

	/**
	 * asd.
	 * @param label a.
	 */
	public void label(String label) {
		this.label = label;
	}

	/**
	 * sd.
	 * @return ad.
	 */
	public Integer minNumberOfProducts() {
		return minNumberOfProducts;
	}

	/**
	 * asd.
	 * @param minNumberOfProducts a. 
	 * @return a.
	 */
	public SaleFilterCriteria minNumberOfProducts(Integer minNumberOfProducts) {
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
	 * @return a.
	 */
	public SaleFilterCriteria maxNumberOfProducts(Integer maxNumberOfProducts) {
		this.maxNumberOfProducts = maxNumberOfProducts;
		return this;
	}	
}
