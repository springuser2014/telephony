package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;
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
			resp.setMessage(""); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", request.getFilters());
		}

		sessionService.validate(request.getSessionDto());

		List<ModelDto> modelz = new ArrayList<ModelDto>();
		List<Model> models = modelsDao.find(request.getFilters());

		for(Model model : models) {
			modelz.add(modelConverter.toModelDto(model));
		}

		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg
		resp.setModels(modelz);
		return resp;
	}

	private boolean validate(ModelFetchRequest request, List<Error> errors) {
		// extract to common
		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if(isNull(request.getFilters())) {
			errors.add(Error.create("filters", "filters cannot be null"));
		}

		return errors.size() == 0;
	}

	private boolean validate(ModelEditRequest request, List<Error> errors) {
		// extract to common
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

		sessionService.validate(request.getSessionDto());

		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setMessage(""); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ model : {} ]", request.getModelDto());
		}

		// TODO move to converter
		// TODO add validation
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
		resp.setMessage(""); // TODO : add localized msg

		return resp;
	}

	@Override
	@Transactional
	public ModelDeleteResponse delete(ModelDeleteRequest request) {

		logger.info("ModelServiceImpl.delete starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ modelId : {} ]", request.getModelId());
		}

		modelsDao.removeById(request.getModelId());

		ModelDeleteResponse response = new ModelDeleteResponse();
		response.setSuccess(true);
		response.setMessage(""); // TODO add localized msg

		return response;
	}
}
