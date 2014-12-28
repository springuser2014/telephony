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
import telephony.core.service.converter.ModelConverter;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.request.ModelDeleteRequest;
import telephony.core.service.dto.request.ModelEditRequest;
import telephony.core.service.dto.request.ModelFetchRequest;
import telephony.core.service.ModelService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.response.ModelDeleteResponse;
import telephony.core.service.dto.response.ModelEditResponse;
import telephony.core.service.dto.response.ModelFetchResponse;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import static telephony.core.assertion.CommonAssertions.isNotEmpty;
import static telephony.core.assertion.CommonAssertions.isNull;
import static telephony.core.assertion.CommonAssertions.isNotNull;

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

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional
	public long count(SessionDto session) {
		return modelsDao.count();
	}


	@Override
	@Transactional
	public ModelFetchResponse fetch(ModelFetchRequest request) {

		logger.info("ModelServiceImpl.fetch starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", request.getFilters());
		}

		List<ModelDto> modelz = new ArrayList<ModelDto>();
		List<Model> models = modelsDao.find(request.getFilters());

		for(Model model : models) {
			modelz.add(modelConverter.toModelDto(model));
		}

		ModelFetchResponse response = new ModelFetchResponse();
		response.setSuccess(true);
		response.setMessage(""); // TODO add localized msg
		response.setModels(modelz);
		return response;
	}

	@Override
	@Transactional
	public ModelEditResponse edit(ModelEditRequest request) {

		logger.info("ModelServiceImpl.edit starts");

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

		ModelEditResponse response = new ModelEditResponse();
		response.setSuccess(true);
		response.setMessage(""); // TODO : add localized msg

		return response;
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
