package telephony.core.query.filter;

import java.util.Date;

/**
 * asd.
 */
public class UserFilterCriteria 
extends AbstractFilterCriteria<UserFilterCriteria> {

	private String email;
	private String permissions;
	
	private Date lastLoginFrom;
	private Date lastLoginTo;
	
	/**
	 * asd.
	 * @return a.
	 */
	public static UserFilterCriteria create() {
		return new UserFilterCriteria();
	}
	
	/**
	 * asd.
	 * @return a.
	 */
	public String email() {
		return email;
	}
	
	/**
	 * asd.
	 * @param email a.
	 * @return a.
	 */
	public UserFilterCriteria email(String email) {
		this.email = email;
		return this;
	}
	
	/**
	 * a.
	 * @return a.
	 */
	public String permissions() {
		return permissions;
	}
	
	/**
	 * asd.
	 * @param permissions a.
	 * @return a.
	 */
	public UserFilterCriteria permissions(String permissions) {
		this.permissions = permissions;
		return this;
	}
	
	/**
	 * asd.
	 * @return a.
	 */
	public Date lastLoginFrom() {
		return lastLoginFrom;
	}
	
	/**
	 * asd.
	 * @param lastLoginFrom asd.
	 * @return asd.
	 */
	public UserFilterCriteria lastLoginFrom(Date lastLoginFrom) {
		this.lastLoginFrom = lastLoginFrom;
		return this;
	}
	
	/**
	 * asd.
	 * @return a.
	 */
	public Date lastLoginTo() {
		return lastLoginTo;
	}
	
	/**
	 * asd.
	 * @param lastLoginTo a.
	 * @return a.
	 */
	public UserFilterCriteria lastLoginTo(Date lastLoginTo) {
		this.lastLoginTo = lastLoginTo;
		return this;
	}
}
