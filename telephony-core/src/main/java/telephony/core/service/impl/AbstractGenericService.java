package telephony.core.service.impl;

import java.util.Collection;

import telephony.core.dao.GenericDao;
import telephony.core.entity.jpa.BaseEntity;
import telephony.core.service.GenericService;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public abstract class AbstractGenericService<E extends BaseEntity, D extends GenericDao<E>>
extends AbstractBasicService<E> 
implements GenericService<E> {
	
	@Inject
	private D dao;
	
    private final Class<D> daoClazz;
	
    public AbstractGenericService() {
    	daoClazz = null;
    }

	public AbstractGenericService(Class<D> clazz) {
		daoClazz = clazz; 
	}
	
	protected D dao() {
		return dao;
	}

	@Transactional
	@Override
	public long count(SessionDto session) {
		return dao.count();
	}

	@Transactional
	@Override
	public E findById(Long id) {
		
		return dao.findById(id);
	}

	@Transactional
	@Override
	public Collection<E> findByIds(Collection<Long> ids) {
		
		return dao.findByIds(ids);
	}
	
	@Transactional
	@Override
	public void save(E entity) {
		
		dao.save(entity);
	}
	
	@Transactional
	@Override
	public void batchSave(Collection<E> entities) {
		
		dao.save(entities);
	}
	
	@Transactional
	@Override
	public E update(E entity) {
		
		return dao.saveOrUpdate(entity);
	}

	@Transactional
	@Override
	public Collection<E> batchUpdate(Collection<E> entities) {
		
		return dao.saveOrUpdate(entities);
	}

	@Transactional
	@Override
	public void remove(E entity) {
		
		dao.remove(entity);
	}

	@Transactional
	@Override
	public void batchRemove(Collection<E> entities) {
		
		dao.remove(entities);
	}

	@Transactional
	@Override
	public void removeById(Long id) {

		dao.removeById(id);
	}

	@Transactional
	@Override
	public void batchRemoveByIds(Collection<Long> ids) {
		
		dao.removeByIds(ids);
	}
}
