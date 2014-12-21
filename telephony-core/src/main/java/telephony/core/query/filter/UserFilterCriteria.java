package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * asd.
 */
public class UserFilterCriteria 
extends AbstractFilterCriteria<UserFilterCriteria> {

	private List<Long> storeIds;
	private List<Long> userIds;
	private String email;
	private Boolean isActive;

	private Date lastLoginFrom;
	private Date lastLoginTo;

	public UserFilterCriteria() {
		this.userIds = new ArrayList<Long>();
		this.storeIds = new ArrayList<Long>();
	}

	public List<Long> getUserIds() {
		return this.userIds;
	}

	public void addUserId(Long userId) {

		if (!userIds.contains(userId)) {
			userIds.add(userId);
		}
	}

	public void removeUserId(Long userId) {

		if (userIds.contains(userId)) {
			userIds.remove(userId);
		}
	}

	/**
	 * asd.
	 * @return a.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * asd.
	 * @param email a.
	 * @return a.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Date getLastLoginFrom() {
		return lastLoginFrom;
	}
	
	/**
	 * asd.
	 * @param lastLoginFrom asd.
	 * @return asd.
	 */
	public void setLastLoginFrom(Date lastLoginFrom) {
		this.lastLoginFrom = lastLoginFrom;
	}
	
	/**
	 * asd.
	 * @return a.
	 */
	public Date getLastLoginTo() {
		return lastLoginTo;
	}
	
	/**
	 * asd.
	 * @param lastLoginTo a.
	 * @return a.
	 */
	public void setLastLoginTo(Date lastLoginTo) {
		this.lastLoginTo = lastLoginTo;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Long> getStoreIds() {
		return storeIds;
	}

	public void addStoreId(Long storeId) {

		if (!storeIds.contains(storeId)) {
			storeIds.add(storeId);
		}
	}

	public void removeStoreId(Long storeId) {

		if(storeIds.contains(storeId)) {
			storeIds.remove(storeId);
		}
	}
}
