package telephony.core.query.filter;

import java.util.Date;

import telephony.core.entity.jpa.Contact;

// TODO : refactor and create builder
/**
 * 
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReportedDateFrom() {
		return reportedDateFrom;
	}

	public void setReportedDateFrom(Date reportedDateFrom) {
		this.reportedDateFrom = reportedDateFrom;
	}

	public Date getReportedDateTo() {
		return reportedDateTo;
	}

	public void setReportedDateTo(Date reportedDateTo) {
		this.reportedDateTo = reportedDateTo;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
}
