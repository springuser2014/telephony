package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.ContactsDao;
import telephony.core.entity.Contact;

public class ContactsDaoImpl extends GenericDaoImpl<Contact> implements ContactsDao {

    public ContactsDaoImpl() {
        super(Contact.class);
    }
}
