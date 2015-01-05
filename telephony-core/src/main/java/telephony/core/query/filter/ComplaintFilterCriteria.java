package telephony.core.query.filter;

import java.util.Date;

public class ComplaintFilterCriteria
extends AbstractFilterCriteria<ComplaintFilterCriteria> {

	private String title;
	private String description;
	
	private Date reportedDateFrom;
	private Date reportedDateTo;
	
	private String phoneNumber;
	private String email;
	private String fax;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
}
