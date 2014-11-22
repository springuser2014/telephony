package telephony.core.service.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.ProducerDao;
import telephony.core.entity.jpa.Producer;
import telephony.core.service.ProducerService;
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

	@Override
	public ProducersFetchResponse fetch(ProducersFetchRequest request)
			throws SessionServiceException, ProducerServiceException {
		return null;
	}

	@Override
	public ProducerEditResponse edit(ProducerEditRequest request)
			throws SessionServiceException, ProducerServiceException {
		return null;
	}

	@Override
	public ProducerDeleteResponse delete(ProducerDeleteRequest request)
			throws SessionServiceException, ProducerServiceException {
		return null;
	}

	@Transactional
	@Override
	public Producer findByLabel(SessionDto session, String label) {

		logger.debug("ProducerServiceImpl.findByLabel starts");        
		logger.debug("params : [ label : {} ]", label);

		return producerDao.findByLabel(label);
	}

	@Override
	@Transactional
	public long count(SessionDto session) {
		
		return producerDao.count();
	}

	@Override
	@Transactional
	public Producer findById(SessionDto session, long id) {		

		logger.debug("ProducerServiceImpl.findById starts");        
		logger.debug("params : [ id: {} ]", id);

		return producerDao.findById(id);
	}

	@Transactional
	@Override
	public Collection<Producer> findById(SessionDto session, Collection<Long> ids) {

		logger.debug("ProducerServiceImpl.findById starts");        
		logger.debug("params : [ size: {} ]", ids.size());

		return producerDao.findByIds(ids);
	}

	@Override
	@Transactional
	public Producer update(SessionDto session, Producer producer) {
		logger.debug("ProducerServiceImpl.update starts");        
		logger.debug("params : [ producer : {} ]", producer);

		return producerDao.saveOrUpdate(producer);
	}

	
	@Override
	@Transactional
	public Collection<Producer> update(SessionDto session, Collection<Producer> coll) {
		logger.debug("ProducerServiceImpl.update starts");        
		logger.debug("params : [ size : {} ]", coll.size());

		return producerDao.saveOrUpdate(coll);
	}

	
	@Override
	@Transactional
	public void removeById(SessionDto session, Long id) {
		logger.debug("ProducerServiceImpl.update starts");        
		logger.debug("params : [ id : {} ]", id);

		producerDao.removeById(id);
	}

	
	@Override
	@Transactional
	public void removeById(SessionDto session, Collection<Long> ids) {
		logger.debug("ProducerServiceImpl.removeById starts");        
		logger.debug("params : [ size : {} ]", ids.size());

		producerDao.removeByIds(ids);
	}

}
