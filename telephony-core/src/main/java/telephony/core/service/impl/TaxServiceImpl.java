package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.TaxDao;
import telephony.core.entity.jpa.Tax;
import telephony.core.service.SessionService;
import telephony.core.service.TaxService;
import telephony.core.service.converter.TaxConverter;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.TaxDto;
import telephony.core.service.dto.request.TaxAddRequest;
import telephony.core.service.dto.request.TaxDeleteRequest;
import telephony.core.service.dto.request.TaxEditRequest;
import telephony.core.service.dto.request.TaxFetchRequest;
import telephony.core.service.dto.response.TaxAddResponse;
import telephony.core.service.dto.response.TaxDeleteResponse;
import telephony.core.service.dto.response.TaxEditResponse;
import telephony.core.service.dto.response.TaxFetchResponse;
import telephony.core.service.exception.SessionServiceException;

import java.util.ArrayList;
import java.util.List;

import telephony.core.service.dto.response.Error;

import static telephony.core.assertion.CommonAssertions.*;


public class TaxServiceImpl
extends AbstractBasicService<Tax>
implements TaxService {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	TaxDao taxDao;

	@Inject
	SessionService sessionService;

	@Inject
	TaxConverter taxConverter;

	@Transactional
	@Override
	public long count(SessionDto session) {
		return taxDao.count();
	}

	// TODO extract to validator
	private boolean validate(TaxFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getFilters())) {
			errors.add(Error.create("filters", "filters cannot be null"));
		}

		if (isNull(request.getFilters().getPage())) {
			errors.add(Error.create("filters.page", "filters.page cannot be null"));
		}

		if (isNull(request.getFilters().getPerPage())) {
			errors.add(Error.create("filters.perPage", "filters.perPage cannot be null"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public TaxFetchResponse fetch(TaxFetchRequest request) throws SessionServiceException {

		logger.info("TaxServiceImpl.fetch starts");
		TaxFetchResponse resp = new TaxFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ request : {} ]", request);
		}

		sessionService.validate(request.getSessionDto());

		List<TaxDto> taxez = new ArrayList<TaxDto>();
		List<Tax> entities = taxDao.fetch(request.getFilters());

		for (Tax entity : entities) {
			taxez.add(taxConverter.toDto(entity));
		}

		resp.setMessage("operation performed successfully"); // TODO : add localized msg
		resp.setSuccess(true);
		resp.setTaxes(taxez);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(TaxAddRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getTaxDto())) {
			errors.add(Error.create("taxDto", "taxDto cannot be empty"));
		}

		// TODO add more cases

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public TaxAddResponse add(TaxAddRequest request) throws SessionServiceException {

		logger.info("TaxServiceImpl.add starts");
		TaxAddResponse resp = new TaxAddResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ taxDto : {} ]", request.getTaxDto());
		}

		sessionService.validate(request.getSessionDto());

		TaxDto dto = request.getTaxDto();
		Tax entity = taxConverter.toEntity(dto);

		taxDao.save(entity);

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO : add localized msg

		return resp;
	}

	// TODO extract to validator
	private boolean validate(TaxEditRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getTaxDto())) {
			errors.add(Error.create("taxDto", "taxDto cannot be null"));
			return false;
		}

		// TODO add more cases

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public TaxEditResponse edit(TaxEditRequest request) throws SessionServiceException {

		logger.info("TaxServiceImpl.edit starts");
		TaxEditResponse resp = new TaxEditResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ editTax : {} ]", request.getTaxDto());
		}

		sessionService.validate(request.getSessionDto());

		TaxDto dto = request.getTaxDto();
		Tax tax = taxDao.findById(dto.getId());

		taxConverter.updateEntity(dto, tax);

		taxDao.saveOrUpdate(tax);

		resp.setMessage("operation performed successfully"); // TODO : add localized msg
		resp.setSuccess(true);

		return resp;

	}

	// TODO extract to validator
	private boolean validate(TaxDeleteRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getTaxId())) {
			errors.add(Error.create("taxId", "taxId cannot by empty"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public TaxDeleteResponse delete(TaxDeleteRequest request) throws SessionServiceException {

		logger.info("TaxServiceImpl.delete starts");
		TaxDeleteResponse resp = new TaxDeleteResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setMessage("validationError");
			resp.setSuccess(false);
			resp.setErrors(errors);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ taxId : {} ] ", request.getTaxId());
		}

		sessionService.validate(request.getSessionDto());
		taxDao.removeById(request.getTaxId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

}
