package telephony.core.dao.impl;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class ContactsDaoImpl extends GenericDaoImpl<Contact> implements ContactsDao {

    /**
     * asd.
     */
    public ContactsDaoImpl() {
        super(Contact.class);
    }
}
