package telephony.core.dao;

import telephony.core.entity.jpa.Contact;

/**
 * asd.
 */
public interface ContactsDao extends GenericDao<Contact> {

	/**
	 * asd.
	 * @param label asd.
	 * @return asd.
	 */
	Contact findByLabel(String label);


}
