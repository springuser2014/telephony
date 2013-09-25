package telephony.core.dao;

import telephony.core.entity.jpa.Contact;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface ContactsDao extends GenericDao<Contact> {

	/**
	 * asd.
	 * @param label asd.
	 * @return asd.
	 */
	Contact findByLabel(String label);


}
