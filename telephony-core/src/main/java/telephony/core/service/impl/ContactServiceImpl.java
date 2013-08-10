package telephony.core.service.impl;

import com.google.inject.Inject;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.service.ContactService;

/**
 * Contacts management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class ContactServiceImpl
    extends AbstractBasicService<Contact> implements ContactService {
	
	@Inject
	private ContactsDao contactsDao;

	@Override
	public long count() {
		return contactsDao.count();
	}
}
