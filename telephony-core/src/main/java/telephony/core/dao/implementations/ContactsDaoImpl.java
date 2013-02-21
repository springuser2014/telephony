package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.ContactsDao;
import telephony.core.entity.Contact;

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
