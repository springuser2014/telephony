package telephony.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.query.filter.ContactFilterCriteria;
import telephony.core.service.ContactService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * Contacts management service.
 */
public class ContactServiceImpl extends AbstractBasicService<Contact> implements
		ContactService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ContactsDao contactsDao;

	@Inject
	private SessionService sessionService;

	@Override
	@Transactional
	public long count(Session session) {
		return contactsDao.count();
	}

	@Override
	@Transactional
	public List<Contact> find(Session session, ContactFilterCriteria filters)
			throws SessionServiceException, ContactServiceException {

		logger.debug("ContactServiceImpl.fetchAll starts");
		logger.debug("params : [filters: {}, session : {}]", filters, session);

		sessionService.validate(session);

		List<Contact> lst = contactsDao.find(filters);
		
		logger.debug("found {} elements", lst.size());
		
		return lst;
	}

	@Override
	@Transactional
	public void add(Session session, Contact newContact) 
			throws SessionServiceException, ContactServiceException {
		
		logger.debug("ContactServiceImpl.addNewContact starts");
		logger.debug("params : [ session: {}, newContact : {}]", session, newContact);

		sessionService.validate(session);

		contactsDao.save(newContact);		
	}

	@Override
	@Transactional
	public void updateContact(Session session, Contact contactToUpdate) 
			throws SessionServiceException, ContactServiceException {
		
		logger.debug("ContactServiceImpl.updateContact starts");
		logger.debug("params : [ session : {}, contact: {}]", session, contactToUpdate);

		sessionService.validate(session);

		contactsDao.saveOrUpdate(contactToUpdate);
	}

	@Override
	@Transactional
	public void deleteContact(Session session, Contact contactToDelete)
			throws SessionServiceException, ContactServiceException {
		
		logger.debug("ContactServiceImpl.deleteContact starts");
		logger.debug("params : [ session: {}, contact : {}]", session, contactToDelete);

		sessionService.validate(session);
		
		contactsDao.removeById(contactToDelete.getId());
	}

	@Override
	@Transactional
	public Contact findByLabel(Session session, String label)
			throws SessionServiceException, ContactServiceException {
		
		logger.debug("ContactServiceImpl.findByLabel starts");
		logger.debug("params : [username : {}, sessionId : {}]", session, label);

		sessionService.validate(session);

		Contact contact = contactsDao.findByLabel(label);
		return contact;
	}

	@Override
	@Transactional
	public Contact findById(Session session, Long contactToDeleteId) 
			throws SessionServiceException {
		
		logger.debug("ContactServiceImpl.findById starts");
		logger.debug("params : [ sessionId : {}, contact  : {}]", session, contactToDeleteId );
		
		sessionService.validate(session);

		Contact contact = contactsDao.findById(contactToDeleteId);
		return contact;
	}
	
	
}
