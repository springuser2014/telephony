package telephony.core.service.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.ProducerDao;
import telephony.core.entity.jpa.Producer;
import telephony.core.service.ProducerService;
import telephony.core.service.bean.Session;

/**
 * asd.
 */
public class ProducerServiceImpl 
extends AbstractBasicService<Producer> 
implements ProducerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private ProducerDao producerDao;

	@Transactional
	@Override
	public Producer findByLabel(Session session, String label) {

		logger.debug("ProducerServiceImpl.findByLabel starts");        
		logger.debug("params : [ label : {} ]", label);

		return producerDao.findByLabel(label);
	}

	@Override
	@Transactional
	public long count() {
		
		return producerDao.count();
	}

	@Override
	@Transactional
	public Producer findById(Session session, long id) {		

		logger.debug("ProducerServiceImpl.findById starts");        
		logger.debug("params : [ id: {} ]", id);

		return producerDao.findById(id);
	}

	@Transactional
	@Override
	public Collection<Producer> findById(Session session, Collection<Long> ids) {

		logger.debug("ProducerServiceImpl.findById starts");        
		logger.debug("params : [ size: {} ]", ids.size());

		return producerDao.findByIds(ids);
	}

	@Override
	@Transactional
	public Producer update(Session session, Producer producer) {
		logger.debug("ProducerServiceImpl.update starts");        
		logger.debug("params : [ producer : {} ]", producer);

		return producerDao.saveOrUpdate(producer);
	}

	
	@Override
	@Transactional
	public Collection<Producer> update(Session session, Collection<Producer> coll) {
		logger.debug("ProducerServiceImpl.update starts");        
		logger.debug("params : [ size : {} ]", coll.size());

		return producerDao.saveOrUpdate(coll);
	}

	
	@Override
	@Transactional
	public void removeById(Session session, Long id) {
		logger.debug("ProducerServiceImpl.update starts");        
		logger.debug("params : [ id : {} ]", id);

		producerDao.removeById(id);
	}

	
	@Override
	@Transactional
	public void removeById(Session session, Collection<Long> ids) {
		logger.debug("ProducerServiceImpl.removeById starts");        
		logger.debug("params : [ size : {} ]", ids.size());

		producerDao.removeByIds(ids);
	}

}
