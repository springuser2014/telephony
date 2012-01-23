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

    public void deleteById(Long id);

    public void deleteByIds(List<Long> ids);

    public void delete(E entity);

    public void delete(List<E> entities);
}
