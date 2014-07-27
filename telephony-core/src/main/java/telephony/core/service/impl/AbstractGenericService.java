package telephony.core.service.impl;

import java.util.Collection;

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
	
	private GenericDao<E> dao;

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public E findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<E> findByIds(Collection<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void save(E entity) {
		dao.save(entity);
	}
	
	@Override
	public void saveBatch(Collection<E> entities) {
	}
	

	@Override
	public E update(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<E> batchUpdate(Collection<E> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBatch(Collection<E> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBatchByIds(Collection<Long> ids) {
		// TODO Auto-generated method stub
		
	}
}
