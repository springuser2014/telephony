package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.query.filter.ContactFilterCriteria;
import telephony.core.service.ContactService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.ContactDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.ContactAddResponse;
import telephony.core.service.dto.response.ContactDeleteResponse;
import telephony.core.service.dto.response.ContactEditResponse;
import telephony.core.service.dto.response.ContactFetchResponse;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.ContactEditDto;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * Contacts management service.
 */
public class ContactServiceImpl 
extends AbstractBasicService<Contact> 
implements ContactService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ContactsDao contactsDao;

	@Inject
	private SessionService sessionService;

	@Override
	@Transactional
	public long count(SessionDto session) {
		return contactsDao.count();
	}

	@Override
	@Transactional
	public ContactFetchResponse fetch(ContactFetchRequest request)
			throws SessionServiceException, ContactServiceException {

		SessionDto session = SessionDto.create(request.getUsername(), request.getSessionId());
		ContactFilterCriteria filters = request.getFilters();
		
		logger.debug("ContactServiceImpl.fetchAll starts");
		logger.debug("params : [filters: {}, session : {}]", filters, session);

		sessionService.validate(session);

		List<Contact> lst = contactsDao.find(filters);
		
		logger.debug("found {} elements", lst.size());
		return new ContactFetchResponse();
//		return lst;
	}

	@Override
	@Transactional
	public ContactAddResponse add(ContactAddRequest request)
			throws SessionServiceException, ContactServiceException {
		
		// TODO add dtos validation
		
		SessionDto session = SessionDto.create(request.getUsername(), request.getSessionId());
		ContactDto dto = request.getNewContact();
		
		logger.debug("ContactServiceImpl.addNewContact starts");
		logger.debug("params : [ session: {}, newContact : {}]", session, dto);

		sessionService.validate(session);
		
		Contact newContact = new Contact();
		newContact.setDetails(dto.getDetails());
		newContact.setLabel(dto.getLabel());
		
		for (String email : dto.getEmails()) {
			newContact.addEmail(email);
		}
		
		for (String email : dto.getPhonenumbers()) {
			newContact.addPhonenumber(email);
		}

		contactsDao.save(newContact);

		return new ContactAddResponse();
	}

	@Override
	@Transactional
	public ContactEditResponse edit(ContactEditRequest req)
			throws SessionServiceException, ContactServiceException {
		
		SessionDto session = SessionDto.create(req.getUsername(), req.getSessionId());
		ContactEditDto dto = req.getContactToEdit();
		
		logger.debug("ContactServiceImpl.updateContact starts");
		logger.debug("params : [ session : {}, contact: {}]", session, dto);

		sessionService.validate(session);
		
		Contact contactToUpdate = contactsDao.findById(dto.getId());
		
		contactToUpdate.setDetails(dto.getDetails());
		contactToUpdate.setLabel(dto.getLabel());

		for (String emailToRemove : dto.getEmailsToRemove()) {
			contactToUpdate.removeEmail(emailToRemove);
		}
		
		for (String phonenumberToRemove : dto.getPhonenumbersToRemove()) {
			contactToUpdate.removePhonenumber(phonenumberToRemove);
		}
		
		for (String emailToAdd : dto.getEmails()) {
			contactToUpdate.addEmail(emailToAdd);
		}
		
		for (String phonenumberToAdd : dto.getPhonenumbers()) {
			contactToUpdate.addPhonenumber(phonenumberToAdd);
		}

		contactsDao.saveOrUpdate(contactToUpdate);

		return new ContactEditResponse();
	}

	@Override
	@Transactional
	public ContactDeleteResponse delete(ContactDeleteRequest req)
			throws SessionServiceException, ContactServiceException {
		
		SessionDto session = SessionDto.create(req.getUsername(), req.getSessionId());
		Long contactId = req.getContactToDelete();
		
		logger.debug("ContactServiceImpl.deleteContact starts");
		logger.debug("params : [ session: {}, contact : {}]", session, contactId);

		sessionService.validate(session);
		
		contactsDao.removeById(contactId);

		return new ContactDeleteResponse();
	}

	@Override
	@Transactional
	public Contact findByLabel(SessionDto session, String label)
			throws SessionServiceException, ContactServiceException {
		
		logger.debug("ContactServiceImpl.findByLabel starts");
		logger.debug("params : [username : {}, sessionId : {}]", session, label);

		sessionService.validate(session);

		Contact contact = contactsDao.findByLabel(label);
		return contact;
	}

	@Override
	@Transactional
	public Contact findById(SessionDto session, Long contactToDeleteId) 
			throws SessionServiceException {
		
		logger.debug("ContactServiceImpl.findById starts");
		logger.debug("params : [ sessionId : {}, contact  : {}]", session, contactToDeleteId );
		
		sessionService.validate(session);

		Contact contact = contactsDao.findById(contactToDeleteId);
		return contact;
	}

}
