package telephony.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.ModelDao;
import telephony.core.entity.jpa.Model;
import telephony.core.service.ModelService;

/**
 * asd.
 */
public class ModelServiceImpl extends AbstractBasicService<Model> implements ModelService {
	
    @Inject
    private ModelDao modelsDao;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	@Transactional
	public Model findByLabel(String label) {

		logger.debug("ModelServiceImpl.findByLabel starts");        
		logger.debug("params : [username : {}, sessionId : {}, label: {}]", 
					new Object[] {label});

		return modelsDao.findByLabel(label);
	}

	@Transactional
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
