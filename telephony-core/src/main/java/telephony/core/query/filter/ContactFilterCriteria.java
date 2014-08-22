package telephony.core.query.filter;

/**
 * asd.
 */
public class ContactFilterCriteria 
extends AbstractFilterCriteria<ContactFilterCriteria>{
	
	private String label;
	private String description;
	private String phonenumber;
	private String email;
	
	/**
	 * asd.
	 * @return as.
	 */
	public static ContactFilterCriteria create() {
		return new ContactFilterCriteria();
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
	 * @return ads.
	 */
	public ContactFilterCriteria label(String label) {
		this.label = label;
		return this;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String description() {
		return description;
	}

	/**
	 * asd.
	 * @param description asd.
	 * @return asd.
	 */
	public ContactFilterCriteria description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String phonenumber() {
		return phonenumber;
	}

	/**
	 * asd.
	 * @param phonenumber asd.
	 * @return asd.
	 */
	public ContactFilterCriteria phonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
		return this;
	}

	/**
	 * asd.
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
	public ContactFilterCriteria email(String email) {
		this.email = email;
		return this;
	}
	
}
