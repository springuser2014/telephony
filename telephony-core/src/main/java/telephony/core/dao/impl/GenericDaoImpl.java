package telephony.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.GenericDao;
import telephony.core.entity.jpa.BaseEntity;
import telephony.core.entity.jpa.User;

import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 * @param <E> asd.
 */
public abstract class GenericDaoImpl<E extends BaseEntity> implements GenericDao<E> {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    private final Class<E> entityClass;

    /**
     * asd.
     * @param clazz asd.
     */
    public GenericDaoImpl(Class<E> clazz) {
        entityClass = clazz;
    }

    /**
     * asd.
     * @return asd.
     */
    protected Class<E> getEntityClass() {
        return entityClass;
    }

    /**
     * asd.
     * @return asd.
     */
    protected EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }

    /**
     * asd.
     * @return asd.
     */
    protected DetachedCriteria prepareCriteria() {
        return prepareCriteria(getEntityClass());
    }

    /**
     * asd.
     * @param entityClass asd.
     * @return asd.
     */
	protected DetachedCriteria prepareCriteria(Class<E> entityClass) {

        return DetachedCriteria.forClass(entityClass);
    }

	@Override
    @SuppressWarnings("unchecked")
	public List<E> findAll() {

        logger.debug("findAll starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        List<E> lst =
        getEntityManager().createQuery("select e from " + entityClass.getName() + " e")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

	@Override
    @SuppressWarnings("unchecked")
    public List<E> findNotRemoved() {

        logger.debug("findUndeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        List<E> lst =
        getEntityManager().createQuery("select e from "
        + entityClass.getName() + " e where e.deleter is null")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

	@Override
    @SuppressWarnings("unchecked")
    public List<E> findRemoved() {

        logger.debug("findDeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("no params");

        List<E> lst = getEntityManager().createQuery("select e from "
        + entityClass.getName() + " e where e.deleter is not null")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

    @Override
    public E findById(Long id) {

        logger.debug("findById starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params: [ id : {} ]  ", id);

        E entity = getEntityManager().find(entityClass, id);

        logger.debug("findById ends");

        return entity;
    }

	@Override
    @SuppressWarnings("unchecked")
    public List<E> findByIds(List<Long> ids) {
        logger.debug("findByIds starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", ids.size());


        List<E> res = getEntityManager().createQuery("select e from "
        + entityClass.getName() + " e where e.id in (?1) ")
                .setParameter(1, ids)
                .getResultList();

        logger.debug("findByIds found number of elements : {} ", res.size());

        return res;
    }

	@Override
    @SuppressWarnings("unchecked")
    public List<E> findUnremovedByIds(List<Long> ids) {
        logger.debug("findUndeletedByIds starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", ids.size());

        List<E> res = getEntityManager().createQuery("select e from "
        + entityClass.getName() + " e where e.id in (?1) and e.deleter is null")
                .setParameter(1, ids)
                .getResultList();

        logger.debug("findUndeletedByIds found number of elements : {} ", res.size());

        return res;
    }

	@Override
    @SuppressWarnings("unchecked")
    public List<E> findRemovedByIds(List<Long> ids) {
        logger.debug("findDeletedByIds starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", ids.size());

        List<E> res = getEntityManager().createQuery("select e from "
        + entityClass.getName() + " e where e.id in (?1) and e.deleter is not null")
                .setParameter(1, ids)
                .getResultList();

        logger.debug("findDeletedByIds found number of elements : {} ", res.size());

        return res;
    }

    @Override
    public E save(E entity) {
        logger.debug("save starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        return getEntityManager().merge(entity);
    }

    @Override
    public E saveOrUpdate(E entity) {
        logger.debug("saveOrUpdate stars ");
        logger.debug("entity type : {} ", entityClass.getName());

        E e = getEntityManager().merge(entity);
        getEntityManager().flush();

        return e;
    }

    @Override
    public List<E> save(List<E> entities) {
        logger.debug("save stars ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", entities.size());

        List<E> res = new ArrayList<E>();

        for (E entity : entities) {
            res.add(getEntityManager().merge(entity));
        }

        return res;
    }

    @Override
    public List<E> saveOrUpdate(List<E> entities) {
        logger.debug("saveOrUpdate starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", entities.size());

        List<E> res = new ArrayList<E>();

        for (E entity : entities) {
            res.add(getEntityManager().merge(entity));
        }

        return res;
    }

    @Override
    public void markAsRemovedById(Long id, Long userId) {
        logger.debug("markAsDeletedById starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params : [ id : {}, userId : {} ]", id, userId);

        int touched = getEntityManager().createQuery("update "
        + entityClass.getName() + " e set e.deleter = ?1 , e.deleteAt = ?2 where e.id = ?3")
                .setParameter(1, userId)
                .setParameter(2, new Date(), TemporalType.TIMESTAMP)
                .setParameter(3, id)
                .executeUpdate();

        logger.debug("markAsDeletedById touched {} elements", touched);
    }

    @Override
    public void markAsRemovedByIds(List<Long> ids, Long userId) {
        logger.debug("markAsDeletedByIds starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params : [ userId : {} ]", userId);
        logger.debug("number of elements: {} ", ids.size());

        int touched = getEntityManager().createQuery("update "
        + entityClass.getName() + " e set e.deleter = ?1 , e.deleteAt = ?2 where e.id in (?3)")
                .setParameter(1, userId)
                .setParameter(2, new Date(), TemporalType.TIMESTAMP)
                .setParameter(3, ids)
                .executeUpdate();

        logger.debug("markAsDeletedByIds touched {} elements", touched);
    }


    @Override
    public E markAsRemoved(E entity, Long userId) {
        logger.debug("markAsDeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params : [ userId : {} ]", userId);

        entity.setDeletedAt(new Date());

        E e = getEntityManager().merge(entity);

        logger.debug("markAsDeleted ends");

        return e;
    }

    @Override
    public List<E> markAsRemoved(List<E> entities, User user) {
        logger.debug("markAsDeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params : [ userId : {} ]", user);
        logger.debug("number of elements: {} ", entities.size());

        List<E> result = new ArrayList<E>();

        for (E entity : entities) {

            entity.setDeletedAt(new Date());
            entity.setDeleter(user);
            E e = getEntityManager().merge(entity);
            result.add(e);
        }

        logger.debug("markAsDeleted ends");

        return result;
    }


    @Override
    public void permanentRemoveById(Long id) {
        logger.debug("permanentDeleteById starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        int updated = getEntityManager()
                .createQuery("delete from " + entityClass.getName() + " e where e.id in (?1)")
                .setParameter(1, id)
                .executeUpdate();

        logger.debug("permanentDeleteById updated {} elements ", updated);
    }

    @Override
    public void permanentRemoveByIds(List<Long> ids) {
        logger.debug("permanentDeleteByIds stars ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", ids.size());

        int updated = getEntityManager()
                .createQuery("delete from " + entityClass.getName() + " e where e.id in (?1)")
                .setParameter(1, ids)
                .executeUpdate();

        logger.debug("permanentDeleteByIds updated {} elements ", updated);
    }

    @Override
    public void permanentRemove(E entity) {
        logger.debug("permanentDelete starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        getEntityManager().remove(entity);

        logger.debug("permanentDelete ends");
    }

    @Override
    public void permanentRemove(List<E> entities) {
        logger.debug("permanentDelete starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of elements : {} ", entities.size());

        for (E entity : entities) {
            getEntityManager().remove(entity);
        }

        logger.debug("permanentDelete ends");
    }
}
