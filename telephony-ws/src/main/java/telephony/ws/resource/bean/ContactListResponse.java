package telephony.ws.resource.bean;

import java.util.List;
import telephony.ws.resource.bean.ContactBean;
/**
 * asd.
 */
public class ContactListResponse {
	
	private List<ContactBean> contacts;
	
	/**
	 * asd.
	 */
	public ContactListResponse() {
		
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public List<ContactBean> getContacts() {
		return contacts;
	}

	/**
	 * asd.
	 * @param contacts asd.
	 */
	public void setContacts(List<ContactBean> contacts) {
		this.contacts = contacts;
	}
	
}
