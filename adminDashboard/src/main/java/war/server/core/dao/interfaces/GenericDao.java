package war.server.core.dao.interfaces;

import war.server.core.entity.common.BaseEntity;

import java.util.List;


public interface GenericDao<E extends BaseEntity> {

    public List<E> findAll();

    public List<E> findUndeleted();

    public List<E> findDeleted();

    public E findById(Long id);

    public List<E> findByIds(List<Long> ids);

    public List<E> findUndeletedByIds(List<Long> ids);

    public List<E> findDeletedByIds(List<Long> ids);

    public void save(E entity);

    public void saveOrUpdate(E entity);

    public void save(List<E> entities);

    public void saveOrUpdate(List<E> entities);
    
    public void markAsDeletedById(Long id, Long userId);
    
    public void markAsDeletedByIds(List<Long> ids, Long userId);
    
    public E markAsDeleted(E entity, Long userId);

    public List<E> markAsDeleted(List<E> entities, Long userId);

    public void permanentDeleteById(Long id);

    public void permanentDeleteByIds(List<Long> ids);

    public void permanentDelete(E entity);

    public void permanentDelete(List<E> entities);
}
