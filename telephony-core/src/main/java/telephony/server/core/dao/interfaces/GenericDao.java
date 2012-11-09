package telephony.server.core.dao.interfaces;

import telephony.server.core.entity.User;
import telephony.server.core.entity.common.BaseEntity;

import java.util.List;


/**
 * The out-of-the-box Tomcat configuration was fine for my purposes,
 * though it’s worth mentioning that if your server is going to be accessed remotely,
 * you may want to change the default Tomcat port.
 * <p/>
 * To do this, you have to modify the Tomcat configuration a bit.
 * One of the things I dislike slightly about installing packages from the
 * Ubuntu repository is that I never know what the default paths are.
 *
 * @param <E>
 */
public interface GenericDao<E extends BaseEntity> {

    /**
     * The out-of-the-box Tomcat configuration was fine for my purposes,
     * though it’s worth mentioning that if your server is going to be accessed remotely,
     * you may want to change the default Tomcat port.
     * <p/>
     * To do this, you have to modify the Tomcat configuration a bit.
     * One of the things I dislike slightly about installing packages from the
     * Ubuntu repository is that I never know what the default paths are.
     *
     * @param <E>
     */
    public List<E> findAll();

    /**
     * The out-of-the-box Tomcat configuration was fine for my purposes,
     * though it’s worth mentioning that if your server is going to be accessed remotely,
     * you may want to change the default Tomcat port.
     */
    public List<E> findUndeleted();

    public List<E> findDeleted();

    public E findById(Long id);

    public List<E> findByIds(List<Long> ids);

    public List<E> findUndeletedByIds(List<Long> ids);

    public List<E> findDeletedByIds(List<Long> ids);

    public E save(E entity);

    public E saveOrUpdate(E entity);

    public List<E> save(List<E> entities);

    public List<E> saveOrUpdate(List<E> entities);

    public void markAsDeletedById(Long id, Long userId);

    public void markAsDeletedByIds(List<Long> ids, Long userId);

    public E markAsDeleted(E entity, Long userId);

    public List<E> markAsDeleted(List<E> entities, User user);

    public void permanentDeleteById(Long id);

    public void permanentDeleteByIds(List<Long> ids);

    public void permanentDelete(E entity);

    public void permanentDelete(List<E> entities);
}
