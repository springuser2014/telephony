package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.BaseEntity;
import telephony.core.entity.jpa.User;


/**
 * Defines set of basic operations on entities.
 *
 * @param <E> Entities superclass interface.
 */
public interface GenericDao<E extends BaseEntity> {

    /**
     * Looks for all entities.
     * @return List consisting all elements.
     */
     List<E> find();
    

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
    List<E> findByIds(List<Long> ids);


    /**
     * Saves submitted entity.
     * @param entity Entity to save.
     * @return Freshly saved entity.
     */
    E save(E entity);

    /**
     * Saves (if not exists yet) or update entity in persistence layer. 
     * @param entity Entity to save or update.
     * @return Freshly saved (updated) entity.
     */
    E saveOrUpdate(E entity);

    /**
     * Saves submitted list of entities.
     * @param entities List of entities to save. 
     * @return List of freshly saved entities.
     */
    List<E> save(List<E> entities);

    /**
     * Saves (if not exists yet) or update entities in persistence layer.
     * @param entities List of entities to save.
     * @return List of freshly saved (updated) entities. 
     */
    List<E> saveOrUpdate(List<E> entities);
    
    
    /**
     * Removes entity with specified id.
     * @param id Entity's id.
     */
    void removeById(Long id);

    /**
     * Removes entities with specified ids.
     * @param ids List of ids entities to remove.
     */
    void removeByIds(List<Long> ids);

    /**
     * Removes indicated entity from persistence layer. 
     * @param entity asd.
     */
    void remove(E entity);

    /**
     * Removes indicated entities from persistence layer.
     * @param entities asd.
     */
    void remove(List<E> entities);
}
