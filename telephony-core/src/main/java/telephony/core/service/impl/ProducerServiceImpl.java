package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.ProducerDao;
import telephony.core.entity.jpa.Producer;
import telephony.core.service.ProducerService;
import telephony.core.service.SessionService;
import telephony.core.service.converter.ProducerConverter;
import telephony.core.service.dto.ProducerDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.ProducerDeleteRequest;
import telephony.core.service.dto.request.ProducerEditRequest;
import telephony.core.service.dto.request.ProducersFetchRequest;
import telephony.core.service.dto.response.ProducerDeleteResponse;
import telephony.core.service.dto.response.ProducerEditResponse;
import telephony.core.service.dto.response.ProducersFetchResponse;
import telephony.core.service.exception.ProducerServiceException;
import telephony.core.service.exception.SessionServiceException;

import telephony.core.service.dto.response.Error;

import static telephony.core.assertion.CommonAssertions.*;

public class ProducerServiceImpl
extends AbstractBasicService<Producer> 
implements ProducerService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	ProducerDao producerDao;

	@Inject
	SessionService sessionService;

	@Inject
	ProducerConverter producerConverter;

	@Transactional
	@Override
	public ProducersFetchResponse fetch(ProducersFetchRequest request)
			throws SessionServiceException, ProducerServiceException {

		logger.info("ProducerServiceImpl.fetch starts");
		ProducersFetchResponse resp = new ProducersFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ] ", request.getFilters());
		}

		sessionService.validate(request.getSessionDto());

		List<Producer> producers = producerDao.fetch(request.getFilters());
		List<ProducerDto> producerDtos = new ArrayList<ProducerDto>();

		for (Producer producer : producers) {
			ProducerDto dto = producerConverter.toProducerDto(producer);
			producerDtos.add(dto);
		}

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setProducers(producerDtos);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(ProducersFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getFilters())) {
			errors.add(Error.create("filters", "filters cannot be empty"));
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
	@Transactional()
	public ProducerEditResponse edit(ProducerEditRequest request)
			throws SessionServiceException, ProducerServiceException {

		logger.info("ProducerServiceImpl.edit starts");
		ProducerEditResponse resp = new ProducerEditResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setMessage("validationError");
			resp.setErrors(errors);
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ editDto : {} ]", request.getProducerDto());
		}

		sessionService.validate(request.getSessionDto());

		Producer producer = producerDao.findById(request.getProducerDto().getProducerId());
		producer.setLabel(request.getProducerDto().getLabel());
		producerDao.saveOrUpdate(producer);
		
		resp.setSuccess(true);
		resp.setMessage("operation performed successfully");
		return resp;
	}

	// TODO extract to validator
	private boolean validate(ProducerEditRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		// TODO add more validation cases

		return errors.size() == 0;
	}

	// TODO extract to validator
	private boolean validate(ProducerDeleteRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getProducerId())) {
			errors.add(Error.create("producerId", "producerId cannot be null"));
		}

		return errors.size() == 0;
	}

	@Override
	public ProducerDeleteResponse delete(ProducerDeleteRequest request)
			throws SessionServiceException, ProducerServiceException {

		logger.info("ProducerServiceImpl.delete starts");
		ProducerDeleteResponse resp = new ProducerDeleteResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setMessage("validationError");
			resp.setSuccess(false);
			resp.setErrors(errors);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ producerId : {} ]", request.getProducerId());
		}

		sessionService.validate(request.getSessionDto());

		producerDao.removeById(request.getProducerId());

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully");

		return resp;
	}

	@Override
	@Transactional
	public long count(SessionDto session) {

		return producerDao.count();
	}
}
