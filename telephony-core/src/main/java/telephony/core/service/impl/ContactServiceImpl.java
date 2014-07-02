package telephony.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.service.ContactService;
import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
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
	public long count() {
		return contactsDao.count();
	}

	@Override
	@Transactional
	public List<Contact> fetchAll(String username, String sessionId)
			throws SessionServiceException, ContactServiceException {

		logger.debug("ContactServiceImpl.fetchAll starts");
		logger.debug("params : [username : {}, sessionId : {}]", new Object[] {
				username, sessionId });

		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);

		List<Contact> lst = contactsDao.find();
		
		logger.debug("found {} elements", lst.size());
		
		return lst;
	}

	@Override
	@Transactional
	public void addNewContact(String username, String sessionId,
			Contact newContact) throws SessionServiceException,
			ContactServiceException {
		
		logger.debug("ContactServiceImpl.addNewContact starts");
		logger.debug("params : [username : {}, sessionId : {}]", new Object[] {
				username, sessionId });

		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);

		contactsDao.save(newContact);		
	}

	@Override
	@Transactional
	public void updateContact(String username, String sessionId,
			Contact contactToUpdate) throws SessionServiceException,
			ContactServiceException {
		logger.debug("ContactServiceImpl.updateContact starts");
		logger.debug("params : [username : {}, sessionId : {}]", new Object[] {
				username, sessionId });

		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);

		contactsDao.saveOrUpdate(contactToUpdate);
	}

	@Override
	@Transactional
	public void deleteContact(String username, String sessionId,
			Contact contactToDelete) throws SessionServiceException,
			ContactServiceException {
		logger.debug("ContactServiceImpl.deleteContact starts");
		logger.debug("params : [username : {}, sessionId : {}]", new Object[] {
				username, sessionId });

		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		contactsDao.removeById(contactToDelete.getId());
	}

	@Override
	@Transactional
	public Contact findByLabel(String username, String sessionId, String label)
			throws SessionServiceException, ContactServiceException {
		logger.debug("ContactServiceImpl.findByLabel starts");
		logger.debug("params : [username : {}, sessionId : {}]", new Object[] {
				username, sessionId });

		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);

		Contact contact = contactsDao.findByLabel(label);
		return contact;
	}

	@Override
	@Transactional
	public Contact findById(String username, String sessionId,
			Long contactToDeleteId) throws SessionServiceException {
		
		logger.debug("ContactServiceImpl.findById starts");
		logger.debug("params : [username : {} , sessionId : {}, contactToDeleteId : {}]",
			new Object[] { username, sessionId, contactToDeleteId});
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);

		Contact contact = contactsDao.findById(contactToDeleteId);
		return contact;
	}
	
	
}
