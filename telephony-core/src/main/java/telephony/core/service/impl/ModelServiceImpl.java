package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ModelDao;
import telephony.core.dao.ProducerDao;
import telephony.core.entity.jpa.Model;
import telephony.core.entity.jpa.Producer;
import telephony.core.service.SessionService;
import telephony.core.service.converter.ModelConverter;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.request.ModelDeleteRequest;
import telephony.core.service.dto.request.ModelEditRequest;
import telephony.core.service.dto.request.ModelFetchRequest;
import telephony.core.service.ModelService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.response.*;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;

import static telephony.core.assertion.CommonAssertions.*;

/**
 * asd.
 */
public class ModelServiceImpl 
extends AbstractBasicService<Model> 
implements ModelService {
	
    @Inject
    ModelDao modelsDao;

	@Inject
	ProducerDao producerDao;

	@Inject
	ModelConverter modelConverter;

	@Inject
	SessionService sessionService;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional
	public long count(SessionDto session) {
		return modelsDao.count();
	}


	@Override
	@Transactional
	public ModelFetchResponse fetch(ModelFetchRequest request) throws SessionServiceException {

		logger.info("ModelServiceImpl.fetch starts");
		ModelFetchResponse resp = new ModelFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", request.getFilters());
		}

		sessionService.validate(request.getSessionDto());

		List<ModelDto> modelz = new ArrayList<ModelDto>();
		List<Model> models = modelsDao.findByCriteria(request.getFilters());

		for(Model model : models) {
			modelz.add(modelConverter.toModelDto(model));
		}

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setModels(modelz);
		return resp;
	}

	// TODO extract to validator
	private boolean validate(ModelFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if(isNull(request.getFilters())) {
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

	// TODO extract to validator
	private boolean validate(ModelEditRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getModelDto())) {
			errors.add(Error.create("modelDto", "modelDto cannot be null"));
		}

		if (isNotNull(request.getModelDto())) {

			if(isEmpty(request.getModelDto().getId())) {
				errors.add(Error.create("modelDto.id", "modelDto.id cannot be empty"));
			}
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public ModelEditResponse edit(ModelEditRequest request) throws SessionServiceException {

		logger.info("ModelServiceImpl.edit starts");
		ModelEditResponse resp = new ModelEditResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ model : {} ]", request.getModelDto());
		}

		sessionService.validate(request.getSessionDto());

		// TODO move to converter
		ModelDto modelDto = request.getModelDto();

		Model model = modelsDao.findById(modelDto.getId());
		Producer producer = null;

		if (isNotEmpty(modelDto.getProducer()) && isNotNull(modelDto.getProducer())) {
			producer = producerDao.findByLabel(modelDto.getProducer());
		}

		if (isNull(producer) && isNotNull(modelDto.getProducerId())) {
			producer = producerDao.findById(modelDto.getProducerId());
		}

		if (isNull(producer) && isNotNull(modelDto.getProducer())) {
			producer = new Producer();
			producer.setLabel(modelDto.getProducer());

			producer = producerDao.saveOrUpdate(producer);
		}

		model.setLabel(modelDto.getLabel());
		model.setProducer(producer);

		modelsDao.saveOrUpdate(model);

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO : add localized msg

		return resp;
	}

	// TODO extract to validator
	private boolean validate(ModelDeleteRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getModelId())) {
			errors.add(Error.create("modelId", "modelId cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public ModelDeleteResponse delete(ModelDeleteRequest request) throws SessionServiceException {

		logger.info("ModelServiceImpl.delete starts");
		ModelDeleteResponse resp = new ModelDeleteResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ modelId : {} ]", request.getModelId());
		}

		sessionService.validate(request.getSessionDto());
		modelsDao.removeById(request.getModelId());

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg

		return resp;
	}
}
