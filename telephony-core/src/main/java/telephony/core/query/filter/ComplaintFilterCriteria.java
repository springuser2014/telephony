package telephony.core.query.filter;

import telephony.core.entity.enumz.ComplaintStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ComplaintFilterCriteria
extends AbstractFilterCriteria<ComplaintFilterCriteria> {

	private List<Long> ignoreIds;
	private String title;
	private String description;
	private ComplaintStatus status;
	
	private Date reportedDateFrom;
	private Date reportedDateTo;
	
	private String phoneNumber;
	private String email;
	private String fax;

	public ComplaintFilterCriteria() {
		ignoreIds = new ArrayList<>();
	}

	public void addIgnoreId(Long id) {

		if (!ignoreIds.contains(id)) {
			ignoreIds.add(id);
		}
	}

	public void removeIgnoreId(Long id) {

		if (ignoreIds.contains(id)) {
			ignoreIds.remove(id);
		}
	}

	public List<Long> getIgnoreIds() {
		return ignoreIds;
	}

	public void setIgnoreIds(List<Long> ignoreIds) {
		this.ignoreIds = ignoreIds;
	}

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

	public ComplaintStatus getStatus() {
		return status;
	}

	public void setStatus(ComplaintStatus status) {
		this.status = status;
	}
}
