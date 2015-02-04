package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Contact;
import telephony.core.query.filter.ContactFilterCriteria;


public interface ContactsDao extends GenericDao<Contact> {

	Contact findByLabel(String label);

	List<Contact> findByCriteria(ContactFilterCriteria filters);

	Long countByCriteria(ContactFilterCriteria filters);


}
