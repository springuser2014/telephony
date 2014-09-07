package telephony.core.query.filter;

import java.util.Date;

import telephony.core.entity.jpa.Contact;

/**
 * asd.
 */
public class ComplaintFilterCriteria 
extends AbstractFilterCriteria<ComplaintFilterCriteria> {

	private String label;
	private String description;
	
	private Date reportedDateFrom;
	private Date reportedDateTo;
	
	private String phoneNumber;
	private String email;
	
	private Long contactId;
	
	/**
	 * asd.
	 * @return asd.
	 */
	public static ComplaintFilterCriteria create() {
		return new ComplaintFilterCriteria();
	}
	
	/**
	 * asd.
	 * @param label asd.
	 * @return asd.
	 */
	public ComplaintFilterCriteria label(String label) {
		this.label = label;
		
		return this;
	}
	
	/**
	 * asd.
	 * @return asd.
	 */
	public String label() {
		return this.label;
	}
	
	/**
	 * asd.
	 * @return a.
	 */
	public String description() {
		return description;
	}

	/**
	 * asd.
	 * @param description a.
	 * @return a.
	 */
	public ComplaintFilterCriteria description(String description) {
		this.description = description;
		
		return this;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Date reportedDateFrom() {
		return reportedDateFrom;
	}

	/**
	 * asd.
	 * @param reportedDateFrom a.
	 * @return a.
	 */
	public ComplaintFilterCriteria reportedDateFrom(Date reportedDateFrom) {
		this.reportedDateFrom = reportedDateFrom;
		
		return this; 
	}

	/**
	 * asd .
	 * @return asd.
	 */
	public Date reportedDateTo() {
		return reportedDateTo;
	}

	/**
	 * asd.
	 * @param reportedDateTo asd.
	 * @return asd.
	 */
	public ComplaintFilterCriteria reportedDateTo(Date reportedDateTo) {
		this.reportedDateTo = reportedDateTo;
		
		return this;
	}

	/**
	 * a.
	 * @return asd.
	 */
	public String phoneNumber() {
		return phoneNumber;
	}

	/**
	 * asd.
	 * @param phoneNumber asd.
	 * @return asd.
	 */
	public ComplaintFilterCriteria phoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		
		return this;
	}

	/**
	 * ad.
	 * @return asd.
	 */
	public String email() {
		return email;
	}

	/**
	 * asd.
	 * @param email asd.
	 * @return asd.
	 */
	public ComplaintFilterCriteria email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Long contact() {
		return contactId;
	}

	/**
	 * asd.
	 * @param contactId asd.
	 * @return asd.
	 */
	public ComplaintFilterCriteria contact(Long contactId) {
		this.contactId = contactId;
		
		return this;
	}	
}
