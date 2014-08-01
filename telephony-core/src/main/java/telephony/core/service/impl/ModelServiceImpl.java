package telephony.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ModelDao;
import telephony.core.entity.jpa.Model;
import telephony.core.service.ModelService;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * asd.
 */
public class ModelServiceImpl 
extends AbstractBasicService<Model> 
implements ModelService {
	
    @Inject
    private ModelDao modelsDao;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	@Transactional
	public Model findByLabel(String label) {

		logger.debug("ModelServiceImpl.findByLabel starts");        
		logger.debug("params : [ label: {} ]", label);

		return modelsDao.findByLabel(label);
	}

	@Transactional
	@Override
	public long count() {
		return modelsDao.count();
	}

	@Override
	@Transactional
	public Model findById(long id) {
		logger.debug("ModelServiceImpl.findById starts");        
		logger.debug("params : [ id: {} ]", id);

		return modelsDao.findById(id);
	}
	

}
