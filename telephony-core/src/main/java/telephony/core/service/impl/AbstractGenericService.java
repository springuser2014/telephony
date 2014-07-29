package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.GenericDao;
import telephony.core.entity.jpa.BaseEntity;
import telephony.core.service.GenericService;

/**
 * asd.
 * @param <E> asd.
 */
public abstract class AbstractGenericService<E extends BaseEntity> 
extends AbstractBasicService<E> 
implements GenericService<E> {
	
	@Inject
	private GenericDao<E> dao;

	@Transactional
	@Override
	public long count() {
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
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Transactional
	@Override
	public void save(E entity) {
		
		getEntityManager().getTransaction().begin();
		
		if (!getEntityManager().contains(entity)) {
			
			dao.save(entity);
//			getEntityManager().persist(entity);
			getEntityManager().flush();
		}
		
		getEntityManager().getTransaction().commit();
//		dao.save(entity);
	}
	
	@Transactional
	@Override
	public void batchSave(Collection<E> entities) {
	}
	
	@Transactional
	@Override
	public E update(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Collection<E> batchUpdate(Collection<E> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void remove(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void batchRemove(Collection<E> entities) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void batchRemoveByIds(Collection<Long> ids) {
		// TODO Auto-generated method stub
		
	}
}
