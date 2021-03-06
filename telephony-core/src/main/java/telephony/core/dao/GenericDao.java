package telephony.core.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import telephony.core.entity.jpa.BaseEntity;
import telephony.core.query.filter.DeliveryFilterCriteria;


/**
 * Defines set of basic operations on entities.
 *
 * @param <E> Entities superclass interface.
 */
public interface GenericDao<E> {
	
	/**
	 * asd.
	 * @return asd.
	 */
	EntityManager getEntityManager();

    /**
     * Looks for entity indentified by pk.
     * @param id Primary key.
     * @return Found entity.
     */
    E findById(Long id);

    /**
     * Looks for set of entities with specified Ids.
     * @param ids Set of entities Ids. 
     * @return Set of found entities.
     */
    Collection<E> findByIds(Collection<Long> ids);


    /**
     * Saves submitted entity.
     * @param entity Entity to save.
     */
    void save(E entity);

    /**
     * Saves (if not exists yet) or update entity in persistence layer. 
     * @param entity Entity to save or update.
     * @return TODO
     */
    E saveOrUpdate(E entity);

    /**
     * Saves submitted list of entities.
     * @param entities List of entities to save. 
     */
    void save(Collection<E> entities);

    /**
     * Saves (if not exists yet) or update entities in persistence layer.
     * @param entities List of entities to save.
     * @return TODO
     */
    List<E> saveOrUpdate(Collection<E> entities);

    /**
     * Removes entities with specified ids.
     * @param ids List of ids entities to remove.
     */
    void removeByIds(Collection<Long> ids);
    
	/**
	 * Removes an entity with given primary key id.
	 * @param id Entity's primary key.
	 */
	void removeById(Long id);
	
    /**
     * Removes indicated entity from persistence layer. 
     * @param entity to remove.
     */
    void remove(E entity);

    /**
     * Removes indicated entities from persistence layer.
     * @param entities to remove.
     */
    void remove(Collection<E> entities);
    
	/**
	 * Counts all entities stores in persistence layer.
	 * @return A number of all entitites.
	 */
	long count();

}
