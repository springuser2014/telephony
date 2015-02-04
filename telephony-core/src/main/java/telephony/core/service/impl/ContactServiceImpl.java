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
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import static telephony.core.assertion.CommonAssertions.isEmpty;
import static telephony.core.assertion.CommonAssertions.isNotEmpty;
import static telephony.core.assertion.CommonAssertions.*;

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

		ContactFetchResponse resp = new ContactFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setMessage("validationError");
			resp.setSuccess(false);
			resp.setErrors(errors);
			return resp;
		}

		ContactFilterCriteria filters = request.getFilters();

		if (logger.isDebugEnabled()) {
			logger.debug("params : [filters: {} : {}]", filters);
		}

		sessionService.validate(request.getSessionDto());
		List<Contact> lst = contactsDao.findByCriteria(filters);

		for(Contact contact : lst) {
			ContactSearchDto dto = contactConverter.contactToContactSearchDto(contact);

			if (isNotNull(dto)) {
				resp.addContact(dto);
			}
		}

		resp.setMessage("operation performed successfully"); // TODO localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(ContactFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getFilters())) {
			errors.add(Error.create("filters", "filters cannot be null"));
			return false;
		}

		if (isNull(request.getFilters().getPage())) {
			errors.add(Error.create("filters.page", "filters.page cannot be null"));
		}

		if(isNull(request.getFilters().getPerPage())) {
			errors.add(Error.create("filters.perPage", "filters.perPage cannot be null"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public ContactAddResponse add(ContactAddRequest request)
			throws SessionServiceException, ContactServiceException {

		logger.info("ContactServiceImpl.addNewContact starts");
		ContactAddResponse resp = new ContactAddResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setMessage("validationError");
			resp.setSuccess(false);
			resp.setErrors(errors);
			return resp;
		}

		ContactDto dto = request.getContact();
		
		if (logger.isDebugEnabled()) {
			logger.debug("params : [ newContact : {}]", dto);
		}

		sessionService.validate(request.getSessionDto());

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

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO : add localized msg

		return resp;
	}

	// TODO extract to validator
	private boolean validate(ContactAddRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getContact())) {
			errors.add(Error.create("contact", "contact cannot be empty"));
		}

		// TODO add more validation cases

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public ContactEditResponse edit(ContactEditRequest request)
			throws SessionServiceException, ContactServiceException {

		logger.info("ContactServiceImpl.updateContact starts");
		ContactEditResponse resp = new ContactEditResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {

			resp.setMessage("validationError");
			resp.setSuccess(false);
			resp.setErrors(errors);

			return resp;
		}

		ContactEditDto dto = request.getContactToEdit();

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ contact: {}]", dto);
		}

		sessionService.validate(request.getSessionDto());
		
		Contact contactToUpdate = contactsDao.findById(dto.getId());

		// TODO extract to converter
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

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg
		return resp;
	}

	// TODO extract to validator
	private boolean validate(ContactEditRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public ContactDeleteResponse delete(ContactDeleteRequest request)
			throws SessionServiceException, ContactServiceException {

		logger.info("ContactServiceImpl.deleteContact starts");
		List<Error> errors = getEmptyErrors();
		ContactDeleteResponse resp = new ContactDeleteResponse();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationFailed");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ contactId : {}]", request.getContactToDelete());
		}

		sessionService.validate(request.getSessionDto());
		contactsDao.removeById(request.getContactToDelete());

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg
		return resp;
	}

	// TODO extract to validator
	private boolean validate(ContactDeleteRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getContactToDelete())) {
			errors.add(Error.create("contactId", "contactId cannot be empty"));
		}

		return errors.size() == 0;
	}

	// TODO extract to validator
	private boolean validate(ContactDetailsRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getContactId())) {
			errors.add(Error.create("contactId", "contactId cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ContactDetailsResponse fetchDetails(ContactDetailsRequest request)
			throws SessionServiceException {

		logger.info("ContactServiceImpl.fetchDetails starts");
		ContactDetailsResponse resp = new ContactDetailsResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {

			resp.setMessage("validationFailed");
			resp.setSuccess(false);
			resp.setErrors(errors);

			return resp;
		}

		sessionService.validate(request.getSessionDto());

		Contact contact = contactsDao.findById(request.getContactId());
		ContactDto contactDto = contactConverter.contactToContactDto(contact);

		resp.setMessage("operation performed successfully");
		resp.setSuccess(true);
		resp.setContact(contactDto);

		return resp;
	}
}
