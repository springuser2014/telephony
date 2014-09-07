package telephony.core.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.query.filter.ContactFilterCriteria;

/**
 * Contacts management DAO.
 */
public class ContactsDaoImpl extends GenericDaoImpl<Contact> implements ContactsDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public ContactsDaoImpl() {
        super(Contact.class);
    }

	@Override
	public Contact findByLabel(String label) {
		logger.info("findByLabel starts ");
		logger.info("params : [ label = {} ]", label);
				
		Contact contact = (Contact) getEntityManager()
				.createQuery("select e from Contact e where e.label = ?1")
				.setParameter(1, label)
				.getSingleResult();

		logger.info("found {} element", contact);
		return contact;
	}

	@Override
	public List<Contact> find(ContactFilterCriteria filters) {
		// TODO Auto-generated method stub
		return null;
	}
}
