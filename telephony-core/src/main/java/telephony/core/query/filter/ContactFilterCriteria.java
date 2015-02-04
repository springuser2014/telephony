package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.List;

public class ContactFilterCriteria
extends AbstractFilterCriteria<ContactFilterCriteria> {

	private List<Long> ignoreIds;
	private List<Long> ids;
	private String label;
	private String details;
	private String phonenumber;
	private String email;
	private String fax;

	public ContactFilterCriteria() {
		ids = new ArrayList<>();
		ignoreIds = new ArrayList<>();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
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

	public List<Long> getIds() {
		return ids;
	}

	public void setId(List<Long> ids) {
		this.ids = ids;
	}

	public void addId(Long id) {

		if (!ids.contains(id)) {
			ids.add(id);
		}
	}

	public void removeId(Long id) {

		if (ids.contains(id)) {
			ids.remove(id);
		}
	}
}
