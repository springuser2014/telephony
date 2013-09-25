package telephony.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.User;

/**
 * Contacts management DAO.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class ContactsDaoImpl extends GenericDaoImpl<Contact> implements ContactsDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public ContactsDaoImpl() {
        super(Contact.class);
    }

    /**
     * {@inheritDoc}
     */
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
}
