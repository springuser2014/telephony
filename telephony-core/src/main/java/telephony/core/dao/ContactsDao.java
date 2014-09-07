package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Contact;
import telephony.core.query.filter.ContactFilterCriteria;

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

	List<Contact> find(ContactFilterCriteria filters);


}
