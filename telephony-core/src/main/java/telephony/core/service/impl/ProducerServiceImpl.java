package telephony.core.service.impl;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
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

/**
 * asd.
 */
public class ProducerServiceImpl 
extends AbstractBasicService<Producer> 
implements ProducerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private ProducerDao producerDao;

	@Inject
	private SessionService sessionService;

	@Transactional
	@Override
	public ProducersFetchResponse fetch(ProducersFetchRequest request)
			throws SessionServiceException, ProducerServiceException {

		logger.info("ProducerServiceImpl.fetch starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ] ", request.getFilters());
		}

		List<Producer> producers = producerDao.fetch(request.getFilters());
		List<ProducerDto> producerDtos = new ArrayList<ProducerDto>();

		for (Producer producer : producers) {
			ProducerDto dto = ProducerConverter.toProducerDto(producer);
			producerDtos.add(dto);
		}

		ProducersFetchResponse resp = new ProducersFetchResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg
		resp.setProducers(producerDtos);

		return resp;
	}

	@Override
	@Transactional()
	public ProducerEditResponse edit(ProducerEditRequest request)
			throws SessionServiceException, ProducerServiceException {

		logger.info("ProducerServiceImpl.edit starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ editDto : {} ]", request.getProducerDto());
		}

		sessionService.validate(request.getSessionDto());
		// TODO add validation

		Producer producer = producerDao.findById(request.getProducerDto().getId());
		producer.setLabel(request.getProducerDto().getLabel());
		producerDao.saveOrUpdate(producer);
		
		ProducerEditResponse resp = new ProducerEditResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO : add localized msg
		return resp;
	}

	@Override
	public ProducerDeleteResponse delete(ProducerDeleteRequest request)
			throws SessionServiceException, ProducerServiceException {

		logger.info("ProducerServiceImpl.delete starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ producerId : {} ]", request.getProducerId());
		}

		sessionService.validate(request.getSessionDto());

		producerDao.removeById(request.getProducerId());

		ProducerDeleteResponse resp = new ProducerDeleteResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO : add localized msg

		return resp;
	}

	@Override
	@Transactional
	public long count(SessionDto session) {

		return producerDao.count();
	}
}
