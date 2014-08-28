package telephony.core.service.impl;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ModelDao;
import telephony.core.entity.jpa.Model;
import telephony.core.service.ModelService;
import telephony.core.service.dto.Session;

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
	public Collection<Model> update(Session session, Collection<Model> coll) {

		logger.debug("ModelServiceImpl.update starts");        
		logger.debug("params : [ models: {} ]", coll.size());
		
		return modelsDao.saveOrUpdate(coll);	
	}

	@Override
	@Transactional
	public void removeById(Session session, Long id) {

		logger.debug("ModelServiceImpl.remove starts");        
		logger.debug("params : [ id: {} ]", id);
		
		modelsDao.removeById(id);		
	}
	
	@Override
	@Transactional
	public void removeById(Session session, Collection<Long> ids) {

		logger.debug("ModelServiceImpl.remove starts");        
		logger.debug("params : [ ids: {} ]", ids.size());
		
		modelsDao.removeByIds(ids);		
	}

	@Transactional
	@Override
	public Model update(Session session, Model model) {
	
		logger.debug("ModelServiceImpl.update starts");        
		logger.debug("params : [ model: {} ]", model);
		
		return modelsDao.saveOrUpdate(model);	
	}	
	
	@Override
	@Transactional
	public Model findByLabel(Session session, String label) {

		logger.debug("ModelServiceImpl.findByLabel starts");        
		logger.debug("params : [ label: {} ]", label);

		return modelsDao.findByLabel(label);
	}
	
	@Override
	@Transactional
	public long count(Session session) {
		return modelsDao.count();
	}

	@Override
	@Transactional
	public Model findById(Session session, long id) {
		logger.debug("ModelServiceImpl.findById starts");        
		logger.debug("params : [ id: {} ]", id);

		return modelsDao.findById(id);
	}
	
	@Override
	@Transactional
	public Collection<Model> findByIds(Session session, List<Long> ids) {
		logger.debug("ModelServiceImpl.findByIds starts");        
		logger.debug("params : [ ids: {} ]", ids.size());

		return modelsDao.findByIds(ids);
	}

	@Transactional
	@Override
	public void remove(Session session, Model model) {
		logger.debug("ModelServiceImpl.remove starts");        
		logger.debug("params : [ model: {} ]", model);

		modelsDao.remove(model);
	}
	
	
}
