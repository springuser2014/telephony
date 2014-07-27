package telephony.core.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.GenericDao;
import telephony.core.entity.jpa.BaseEntity;

import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * General entities management DAO.

 * @param <E> asd.
 */
public abstract class GenericDaoImpl<E extends BaseEntity> implements GenericDao<E> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    private final Class<E> entityClass;
    
    /**
     * Setup entity's class.
     * @param clazz asd.
     */
    public GenericDaoImpl(Class<E> clazz) {
        entityClass = clazz;
    }

    /**
     * Gets entity's class.
     * @return asd.
     */
    protected Class<E> getEntityClass() {
        return entityClass;
    }

    /**
     * Gets entity's manager.
     * @return asd.
     */
    public EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }

	@Override
    @SuppressWarnings("unchecked")
	public List<E> find() {

        logger.debug("findAll starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        List<E> lst =
        getEntityManager()
        	.createQuery("select e from " + entityClass.getName() + " e")
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
    public Collection<E> findByIds(Collection<Long> ids) {
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
    public void save(E entity) {
        logger.debug("save starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        getEntityManager().persist(entity);
    }

    @Override
    public E saveOrUpdate(E entity) {
        logger.info("saveOrUpdate stars ");
        logger.info("entity type : {} ", entityClass.getName());

        return getEntityManager().merge(entity);
    }

    @Override
    public void save(List<E> entities) {
        logger.debug("save stars ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", entities.size());

        for (E entity : entities) {
            getEntityManager().persist(entity);
        }
    }

    @Override
    public List<E> saveOrUpdate(List<E> entities) {
        logger.debug("saveOrUpdate starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", entities.size());
        List<E> col = new ArrayList<E>();
        
        for (E entity : entities) {
            col.add(getEntityManager().merge(entity));
        }
        
        return col;
    }
    
    @Override
    public void removeById(Long id) {
        logger.debug("removeById starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        int updated = getEntityManager()
                .createQuery("delete from " + entityClass.getName() + " e where e.id in (?1)")
                .setParameter(1, id)
                .executeUpdate();

        logger.debug("removeById updated {} elements ", updated);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        logger.debug("removeByIds stars ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of params : {} ", ids.size());

        int updated = getEntityManager()
                .createQuery("delete from " + entityClass.getName() + " e where e.id in (?1)")
                .setParameter(1, ids)
                .executeUpdate();

        logger.debug("removeByIds updated {} elements ", updated);
    }

    @Override
    public void remove(E entity) {
        logger.debug("remove starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        getEntityManager().remove(entity);

        logger.debug("remove ends");
    }

    @Override
    public void remove(List<E> entities) {
        logger.debug("remove starts ");
        logger.debug("entity type : {} ", entityClass.getName());
        logger.debug("number of elements : {} ", entities.size());

        for (E entity : entities) {
            getEntityManager().remove(entity);
        }

        logger.debug("remove ends");
    }
    
	@Override
	public long count() {
		logger.debug("count starts");
		logger.debug("entity type : {} ", entityClass.getName());
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(entityClass)));
		
		return getEntityManager().createQuery(cq).getSingleResult();
	}
}