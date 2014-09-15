package telephony.core.query.filter;

import java.util.Date;

/**
 * asd.
 */
public class SaleFilterCriteria
extends AbstractFilterCriteria<SaleFilterCriteria> {

	private Date saleDateStart;
	private Date saleDateEnd;
	
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
	public Date getSaleDateStart() {
		return saleDateStart;
	}

	/**
	 * asd.
	 * @param saleDateStart a.
	 * @return a.
	 */
	public SaleFilterCriteria setSaleDateStart(Date saleDateStart) {
		this.saleDateStart = saleDateStart;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Date getSaleDateEnd() {
		return saleDateEnd;
	}

	/**
	 * asd.
	 * @param saleDateEnd a.
	 * @return ad.
	 */
	public SaleFilterCriteria setSaleDateEnd(Date saleDateEnd) {
		this.saleDateEnd = saleDateEnd;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Double getSumFrom() {
		return sumFrom;
	}

	/**
	 * asd.
	 * @param sumFrom a.
	 * @return a.
	 */
	public SaleFilterCriteria setSumFrom(Double sumFrom) {
		this.sumFrom = sumFrom;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Double getSumTo() {
		return sumTo;
	}

	/**
	 * asd.
	 * @param sumTo a.
	 * @return asd.
	 */
	public SaleFilterCriteria setSumTo(Double sumTo) {
		this.sumTo = sumTo;
		return this;
	}

	/**
	 * ads.
	 * @return a.
	 */
	public Long getSoldBy() {
		return deliveredBy;
	}

	/**
	 * asd.
	 * @param soldBy a.
	 * @return ad.
	 */
	public SaleFilterCriteria setSoldBy(Long soldBy) {
		this.deliveredBy = soldBy;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Long getRegisteredBy() {
		return registeredBy;
	}

	/**
	 * asd.
	 * @param registeredBy a.
	 * @return ad.
	 */
	public SaleFilterCriteria setRegisteredBy(Long registeredBy) {
		this.registeredBy = registeredBy;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * asd.
	 * @param label a.
	 */
	public SaleFilterCriteria setLabel(String label) {
		this.label = label;
		return this;
	}

	/**
	 * sd.
	 * @return ad.
	 */
	public Integer getMinNumberOfProducts() {
		return minNumberOfProducts;
	}

	/**
	 * asd.
	 * @param minNumberOfProducts a. 
	 * @return a.
	 */
	public SaleFilterCriteria setMinNumberOfProducts(Integer minNumberOfProducts) {
		this.minNumberOfProducts = minNumberOfProducts;
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Integer getMaxNumberOfProducts() {
		return maxNumberOfProducts;
	}

	/**
	 * asd.
	 * @param maxNumberOfProducts a.
	 * @return a.
	 */
	public SaleFilterCriteria setMaxNumberOfProducts(Integer maxNumberOfProducts) {
		this.maxNumberOfProducts = maxNumberOfProducts;
		return this;
	}	
}
