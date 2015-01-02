package telephony.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.*;
import telephony.core.query.filter.ContactFilterCriteria;
import telephony.core.service.ContactService;
import telephony.core.service.SessionService;
import telephony.core.service.converter.ContactConverter;
import telephony.core.service.dto.*;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.ContactAddResponse;
import telephony.core.service.dto.response.ContactDeleteResponse;
import telephony.core.service.dto.response.ContactEditResponse;
import telephony.core.service.dto.response.ContactFetchResponse;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import static telephony.core.assertion.CommonAssertions.isNotEmpty;
import static telephony.core.assertion.CommonAssertions.isNotNull;


/**
 * Contacts management service.
 */
public class ContactServiceImpl 
extends AbstractBasicService<Contact> 
implements ContactService {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ContactsDao contactsDao;

	@Inject
	SessionService sessionService;

	@Inject
	ContactConverter contactConverter;

	@Override
	@Transactional
	public long count(SessionDto session) {
		return contactsDao.count();
	}

	@Override
	@Transactional
	public ContactFetchResponse fetch(ContactFetchRequest request)
			throws SessionServiceException, ContactServiceException {

		logger.info("ContactServiceImpl.fetchAll starts");

		SessionDto session = SessionDto.create(request.getUsername(), request.getSessionId());
		ContactFilterCriteria filters = request.getFilters();

		if (logger.isDebugEnabled()) {
			logger.debug("params : [filters: {}, session : {}]", filters, session);
		}

		sessionService.validate(session);

		List<Contact> lst = contactsDao.find(filters);

		logger.info("found {} elements", lst.size());

		ContactFetchResponse response = new ContactFetchResponse();

		for(Contact contact : lst) {
			ContactDto dto = contactConverter.contactToContactDto(contact);

			if (isNotNull(dto)) {
				response.addContact(dto);
			}
		}

		response.setMessage(""); // TODO localized msg
		response.setSuccess(true);

		return response;
	}

	@Override
	@Transactional
	public ContactAddResponse add(ContactAddRequest request)
			throws SessionServiceException, ContactServiceException {

		logger.info("ContactServiceImpl.addNewContact starts");

		// TODO add dtos validation
		
		SessionDto session = SessionDto.create(request.getUsername(), request.getSessionId());
		ContactDto dto = request.getNewContact();
		
		if (logger.isDebugEnabled()) {
			logger.debug("params : [ session: {}, newContact : {}]", session, dto);
		}

		sessionService.validate(session);
		// TODO extract to converter
		Address address = new Address();
		address.setAddressLine1(dto.getAddress().getAddressLine1());
		address.setAddressLine2(dto.getAddress().getAddressLine2());
		address.setCountry(dto.getAddress().getCountry());
		address.setCity(dto.getAddress().getCity());
		address.setZipCode(dto.getAddress().getZipCode());
		
		Contact newContact = new Contact();
		newContact.setDetails(dto.getDetails());
		newContact.setLabel(dto.getLabel());
		newContact.setAddress(address);

		if (isNotNull(dto.getEmails())) {
			for (String email : dto.getEmails()) {
				newContact.addEmail(new Email(email));
			}
		}

		if (isNotNull(dto.getPhoneNumbers())) {
			for (PhoneNumberDto phoneNumber : dto.getPhoneNumbers()) {
				PhoneNumber pn = new PhoneNumber();
				pn.setPrefix(phoneNumber.getPrefix());
				pn.setContent(phoneNumber.getNumber());
				newContact.addPhoneNumber(pn);
			}
		}

		if (isNotNull(dto.getFaxes())) {
			for (String fax : dto.getFaxes()) {
				newContact.addFax(new Fax(fax));
			}
		}

		contactsDao.save(newContact);
		ContactAddResponse resp = new ContactAddResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO : add localized msg
		return resp;
	}

	@Override
	@Transactional
	public ContactEditResponse edit(ContactEditRequest req)
			throws SessionServiceException, ContactServiceException {

		logger.info("ContactServiceImpl.updateContact starts");

		SessionDto session = SessionDto.create(req.getUsername(), req.getSessionId());
		ContactEditDto dto = req.getContactToEdit();
		
		if (logger.isDebugEnabled()) {
			logger.debug("params : [ session : {}, contact: {}]", session, dto);
		}

		sessionService.validate(session);
		
		Contact contactToUpdate = contactsDao.findById(dto.getId());

		if (isNotNull(dto.getDetails())) {
			contactToUpdate.setDetails(dto.getDetails());
		}
		if (isNotNull(dto.getLabel())) {
			contactToUpdate.setLabel(dto.getLabel());
		}

		if (isNotNull(dto.getAddress())) {
			AddressDto adto = dto.getAddress();

			if (isNotNull(adto.getAddressLine1())) {
				contactToUpdate.getAddress().setAddressLine1(dto.getAddress().getAddressLine1());
			}
			if (isNotNull(adto.getAddressLine2())) {
				contactToUpdate.getAddress().setAddressLine2(dto.getAddress().getAddressLine2());
			}
			if (isNotNull(adto.getCity())) {
				contactToUpdate.getAddress().setCity(dto.getAddress().getCity());
			}
			if (isNotNull(adto.getZipCode())) {
				contactToUpdate.getAddress().setZipCode(dto.getAddress().getZipCode());
			}
			if (isNotNull(adto.getCountry())) {
				contactToUpdate.getAddress().setCountry(dto.getAddress().getCountry());
			}
		}

		// remove old elements
		if (isNotEmpty(dto.getEmailsToRemove())) {
			for (String emailToRemove : dto.getEmailsToRemove()) {
				Email toRemove = new Email(emailToRemove);
				contactToUpdate.removeEmail(toRemove);
			}
		}

		if (isNotEmpty(dto.getPhoneNumbersToRemove())) {
			for (PhoneNumberDto phoneNumberToRemove : dto.getPhoneNumbersToRemove()) {
				PhoneNumber toRemove = new PhoneNumber(
						phoneNumberToRemove.getPrefix(),
						phoneNumberToRemove.getNumber()
				);

				contactToUpdate.removePhoneNumber(toRemove);
			}
		}

		if (isNotEmpty(dto.getFaxesToRemove())) {
			for (String faxToRemove : dto.getFaxesToRemove()) {
				Fax toRemove = new Fax(faxToRemove);
				contactToUpdate.removeFax(toRemove);
			}
		}

		// add new elements
		if (isNotEmpty(dto.getEmails())) {
			for (String emailToAdd : dto.getEmails()) {
				Email toAdd = new Email(emailToAdd);
				contactToUpdate.addEmail(toAdd);
			}
		}

		if (isNotEmpty(dto.getPhoneNumbers())) {
			for (PhoneNumberDto phoneNumberToAdd : dto.getPhoneNumbers()) {
				PhoneNumber toAdd = new PhoneNumber(
						phoneNumberToAdd.getPrefix(),
						phoneNumberToAdd.getNumber()
				);

				contactToUpdate.addPhoneNumber(toAdd);
			}
		}

		if (isNotEmpty(dto.getFaxes())) {
			for (String faxToAdd : dto.getFaxes()) {
				Fax toAdd = new Fax(faxToAdd);
				contactToUpdate.addFax(toAdd);
			}
		}

		contactsDao.saveOrUpdate(contactToUpdate);

		ContactEditResponse resp = new ContactEditResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg
		return resp;
	}

	@Override
	@Transactional
	public ContactDeleteResponse delete(ContactDeleteRequest req)
			throws SessionServiceException, ContactServiceException {

		logger.info("ContactServiceImpl.deleteContact starts");

		SessionDto session = SessionDto.create(req.getUsername(), req.getSessionId());
		Long contactId = req.getContactToDelete();
		
		if (logger.isDebugEnabled()) {
			logger.debug("params : [ session: {}, contact : {}]", session, contactId);
		}

		sessionService.validate(session);
		
		contactsDao.removeById(contactId);

		ContactDeleteResponse resp = new ContactDeleteResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg
		return resp;
	}
}
