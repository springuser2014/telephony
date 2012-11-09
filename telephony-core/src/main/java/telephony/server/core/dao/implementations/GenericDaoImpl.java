package telephony.server.core.dao.implementations;

import com.google.inject.Inject;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.server.core.dao.interfaces.GenericDao;
import telephony.server.core.entity.User;
import telephony.server.core.entity.common.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class GenericDaoImpl<E extends BaseEntity> implements GenericDao<E> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    protected EntityManager em;

    protected Class entityClass;

    public GenericDaoImpl(Class clazz) {
        entityClass = clazz;
    }

    protected Class getEntityClass() {
        return entityClass;
    }

    protected DetachedCriteria prepareCriteria() {
        return prepareCriteria(getEntityClass());
    }

    protected DetachedCriteria prepareCriteria(Class entityClass) {

        return DetachedCriteria.forClass(entityClass);
    }

    public List<E> findAll() {

        logger.debug("findAll starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        List<E> lst = em.createQuery("select e from " + entityClass.getName() + " e")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

    public List<E> findUndeleted() {

        logger.debug("findUndeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        List<E> lst = em.createQuery("select e from " + entityClass.getName() + " e where e.deleter is null")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

    public List<E> findDeleted() {

        logger.debug("findDeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("no params");

        List<E> lst = em.createQuery("select e from " + entityClass.getName() + " e where e.deleter is not null")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

    public E findById(Long id) {

        logger.debug("findById starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params: [ id : {} ]  ", id);

        E entity = (E) em.find(entityClass, id);

        logger.debug("findById ends");

        return entity;
    }

    public List<E> findByIds(List<Long> ids) {
        logger.debug("findByIds starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", ids.size());


        List<E> res = em.createQuery("select e from " + entityClass.getName() + " e where e.id in (?1) ")
                .setParameter(1, ids)
                .getResultList();

        logger.debug("findByIds found number of elements : {} ", res.size());

        return res;
    }

    public List<E> findUndeletedByIds(List<Long> ids) {
        logger.debug("findUndeletedByIds starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", ids.size());

        List<E> res = em.createQuery("select e from " + entityClass.getName() + " e where e.id in (?1) and e.deleter is null")
                .setParameter(1, ids)
                .getResultList();

        logger.debug("findUndeletedByIds found number of elements : {} ", res.size());

        return res;
    }

    public List<E> findDeletedByIds(List<Long> ids) {
        logger.debug("findDeletedByIds starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", ids.size());

        List<E> res = em.createQuery("select e from " + entityClass.getName() + " e where e.id in (?1) and e.deleter is not null")
                .setParameter(1, ids)
                .getResultList();

        logger.debug("findDeletedByIds found number of elements : {} ", res.size());

        return res;
    }

    public E save(E entity) {
        logger.debug("save starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        return em.merge(entity);
    }

    public E saveOrUpdate(E entity) {
        logger.debug("saveOrUpdate stars ");
        logger.debug("entity type : {} ", entityClass.getName());

        return em.merge(entity);
    }

    public List<E> save(List<E> entities) {
        logger.debug("save stars ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", entities.size());

        List<E> res = new ArrayList<E>();

        for (E entity : entities) {
            res.add(em.merge(entity));
        }

        return res;
    }

    public List<E> saveOrUpdate(List<E> entities) {
        logger.debug("saveOrUpdate starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", entities.size());

        List<E> res = new ArrayList<E>();

        for (E entity : entities) {
            res.add(em.merge(entity));
        }

        return res;
    }

    public void markAsDeletedById(Long id, Long userId) {
        logger.debug("markAsDeletedById starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params : [ id : {}, userId : {} ]", id, userId);

        int touched = em.createQuery("update " + entityClass.getName() + " e set e.deleter = ?1 , e.deleteAt = ?2 where e.id = ?3")
                .setParameter(1, userId)
                .setParameter(2, new Date(), TemporalType.TIMESTAMP)
                .setParameter(3, id)
                .executeUpdate();

        logger.debug("markAsDeletedById touched {} elements", touched);
    }

    public void markAsDeletedByIds(List<Long> ids, Long userId) {
        logger.debug("markAsDeletedByIds starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params : [ userId : {} ]", userId);
        logger.debug("number of elements: {} ", ids.size());

        int touched = em.createQuery("update " + entityClass.getName() + " e set e.deleter = ?1 , e.deleteAt = ?2 where e.id in (?3)")
                .setParameter(1, userId)
                .setParameter(2, new Date(), TemporalType.TIMESTAMP)
                .setParameter(3, ids)
                .executeUpdate();

        logger.debug("markAsDeletedByIds touched {} elements", touched);
    }


    public E markAsDeleted(E entity, Long userId) {
        logger.debug("markAsDeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params : [ userId : {} ]", userId);

        entity.setDeletedAt(new Date());
//        entity.setDeletedBy(userId);

        E e = em.merge(entity);

        logger.debug("markAsDeleted ends");

        return e;
    }

    public List<E> markAsDeleted(List<E> entities, User user) {
        logger.debug("markAsDeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("params : [ userId : {} ]", user);
        logger.debug("number of elements: {} ", entities.size());

        List<E> result = new ArrayList<E>();

        for (E entity : entities) {

            entity.setDeletedAt(new Date());
            entity.setDeleter(user);
            E e = em.merge(entity);
            result.add(e);
        }

        logger.debug("markAsDeleted ends");

        return result;
    }


    public void permanentDeleteById(Long id) {
        logger.debug("permanentDeleteById starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        int updated = em.createQuery("delete from " + entityClass.getName() + " e where e.id in (?1)")
                .setParameter(1, id)
                .executeUpdate();

        logger.debug("permanentDeleteById updated {} elements ", updated);
    }

    public void permanentDeleteByIds(List<Long> ids) {
        logger.debug("permanentDeleteByIds stars ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", ids.size());

        int updated = em.createQuery("delete from " + entityClass.getName() + " e where e.id in (?1)")
                .setParameter(1, ids)
                .executeUpdate();

        logger.debug("permanentDeleteByIds updated {} elements ", updated);
    }

    public void permanentDelete(E entity) {
        logger.debug("permanentDelete starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        em.remove(entity);

        logger.debug("permanentDelete ends");
    }

    public void permanentDelete(List<E> entities) {
        logger.debug("permanentDelete starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of elements : {} ", entities.size());

        for (E entity : entities) {
            em.remove(entity);
        }

        logger.debug("permanentDelete ends");
    }
}
