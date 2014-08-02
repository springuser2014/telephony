package telephony.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.ProducerDao;
import telephony.core.entity.jpa.Producer;
import telephony.core.service.ProducerService;

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
	public Producer findByLabel(String label) {

		return producerDao.findByLabel(label);
	}

	@Transactional
	@Override
	public long count() {
		
		return producerDao.count();
	}

	@Transactional
	@Override
	public Producer findById(long id) {
		
		return producerDao.findById(id);
	}

}
