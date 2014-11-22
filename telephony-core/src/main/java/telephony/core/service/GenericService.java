package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.BaseEntity;

/**
 * asd.
 * @param <T> asd.
 * @param <D> foo.
 */

////////////////////////////////
// TODO remove the stuff below
////////////////////////////////

@Deprecated
public interface GenericService<T extends BaseEntity> 
extends BasicService<T> {
	
	/**
	 * asd.
	 * @param id a.
	 * @return a.
	 */
	T findById(Long id);
	
	/**
	 * asd.
	 * @param ids a
	 * @return a.
	 */
	Collection<T> findByIds(Collection<Long> ids);
	
	/**
	 * asd.
	 * @param entity a.
	 */
	void save(T entity);
	
	/**
	 * asd.
	 * @param entities a.
	 */
	void batchSave(Collection<T> entities);
	
	/**
	 * asd.
	 * @param entity a.
	 * @return a.
	 */
	T update(T entity);
	
	/**
	 * asd.
	 * @param entities a.
	 * @return a.
	 */
	Collection<T> batchUpdate(Collection<T> entities);
	
	/**
	 * asd.
	 * @param entity a.
	 */
	void remove(T entity);
	
	/**
	 * asd.
	 * @param entities a.
	 */
	void batchRemove(Collection<T> entities);
	
	/**
	 * asd.
	 * @param id a.
	 */
	void removeById(Long id);
	
	/**
	 * ad.
	 * @param ids asd.
	 */
	void batchRemoveByIds(Collection<Long> ids);
}
