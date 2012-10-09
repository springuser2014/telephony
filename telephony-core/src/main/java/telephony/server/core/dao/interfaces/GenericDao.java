package telephony.server.core.dao.interfaces;

import telephony.server.core.entity.User;
import telephony.server.core.entity.common.BaseEntity;

import java.util.List;


public interface GenericDao<E extends BaseEntity> {

    public List<E> findAll();

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
