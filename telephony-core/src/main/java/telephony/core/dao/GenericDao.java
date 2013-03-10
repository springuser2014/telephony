package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.BaseEntity;
import telephony.core.entity.jpa.User;


/**
 * Defines set of basic operations on entities.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 * @param <E> Entities superclass interface.
 */
public interface GenericDao<E extends BaseEntity> {

    /**
     * Looks for all entities.
     * @return List consisting all elements.
     */
     List<E> findAll();

    /**
     * asd.
     * @return asd.
     */
    List<E> findNotRemoved();

    /**
     * asd.
     * @return asd.
     */
    List<E> findRemoved();

    /**
     * asd.
     * @param id asd.
     * @return asd.
     */
    E findById(Long id);

    /**
     * asd.
     * @param ids asd.
     * @return asd.
     */
    List<E> findByIds(List<Long> ids);

    /**
     * asd.
     * @param ids asd.
     * @return asd.
     */
    List<E> findUnremovedByIds(List<Long> ids);

    /**
     * asd.
     * @param ids asd.
     * @return asd.
     */
    List<E> findRemovedByIds(List<Long> ids);

    /**
     * asd.
     * @param entity asd.
     * @return asd.
     */
    E save(E entity);

    /**
     * asd.
     * @param entity asd.
     * @return asd.
     */
    E saveOrUpdate(E entity);

    /**
     * asd.
     * @param entities asd.
     * @return asd.
     */
    List<E> save(List<E> entities);

    /**
     * asd.
     * @param entities asd.
     * @return asd.
     */
    List<E> saveOrUpdate(List<E> entities);

    /**
     * asd.
     * @param id asd.
     * @param userId asd.
     */
    void markAsRemovedById(Long id, Long userId);

    /**
     * asd.
     * @param ids asd.
     * @param userId asd.
     */
    void markAsRemovedByIds(List<Long> ids, Long userId);

    /**
     * asd.
     * @param entity asd.
     * @param userId asd.
     * @return asd.
     */
    E markAsRemoved(E entity, Long userId);

    /**
     * asd.
     * @param entities asd.
     * @param user asd.
     * @return asd.
     */
    List<E> markAsRemoved(List<E> entities, User user);

    /**
     * asd.
     * @param id asd.
     */
    void permanentRemoveById(Long id);

    /**
     * asd.
     * @param ids asd.
     */
    void permanentRemoveByIds(List<Long> ids);

    /**
     * asd.
     * @param entity asd.
     */
    void permanentRemove(E entity);

    /**
     * asd.
     * @param entities asd.
     */
    void permanentRemove(List<E> entities);
}
