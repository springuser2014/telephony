package telephony.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;

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
}
