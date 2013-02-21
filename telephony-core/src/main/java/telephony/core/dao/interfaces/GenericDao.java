package telephony.core.dao.interfaces;

import java.util.List;

import telephony.core.entity.BaseEntity;
import telephony.core.entity.User;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 * @param <E> asd.
 */
public interface GenericDao<E extends BaseEntity> {

    /**
     * asd.
     * @return asd.
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
    List<E> findDeleted();

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
    List<E> findUndeletedByIds(List<Long> ids);

    /**
     * asd.
     * @param ids asd.
     * @return asd.
     */
    List<E> findDeletedByIds(List<Long> ids);

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
    void markAsDeletedById(Long id, Long userId);

    /**
     * asd.
     * @param ids asd.
     * @param userId asd.
     */
    void markAsDeletedByIds(List<Long> ids, Long userId);

    /**
     * asd.
     * @param entity asd.
     * @param userId asd.
     * @return asd.
     */
    E markAsDeleted(E entity, Long userId);

    /**
     * asd.
     * @param entities asd.
     * @param user asd.
     * @return asd.
     */
    List<E> markAsDeleted(List<E> entities, User user);

    /**
     * asd.
     * @param id asd.
     */
    void permanentDeleteById(Long id);

    /**
     * asd.
     * @param ids asd.
     */
    void permanentDeleteByIds(List<Long> ids);

    /**
     * asd.
     * @param entity asd.
     */
    void permanentDelete(E entity);

    /**
     * asd.
     * @param entities asd.
     */
    void permanentDelete(List<E> entities);
}
