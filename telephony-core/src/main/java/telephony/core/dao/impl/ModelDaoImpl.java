package telephony.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.ModelDao;
import telephony.core.entity.jpa.Model;
import telephony.core.query.filter.ModelFilterCriteria;

import javax.persistence.Query;
import java.util.List;

import static telephony.core.assertion.CommonAssertions.isNotNull;

/**
 * asd.
 */
public class ModelDaoImpl 
extends GenericDaoImpl<Model> 
implements ModelDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * asd.
	 */
	public ModelDaoImpl() {
		super(Model.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Model findByLabel(String label) {
		
        logger.info("ModelDaoImpl.findByLabel starts");
        
		String queryStr = " select m from Model m where m.label like ?1 ";
        
		if (logger.isDebugEnabled()) {
			logger.debug("queryStr " + queryStr);
		}
		
		Query q = getEntityManager().createQuery(queryStr);
		
		q.setParameter(1, "%" + label + "%");
		
		List<Model> lst = (List<Model>) q.getResultList();
		
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Model> find(ModelFilterCriteria filters) {
		logger.info("ModelDaoImpl.find starts");

		StringBuilder query = new StringBuilder();
		query.append(" select m from Model m inner join m.producer p ");

		boolean whereAdded = false;

		if (isNotNull(filters.getLabel())) {
			query.append(" where m.label like :modelLabel ");
			whereAdded = true;
		}

		if (isNotNull(filters.getModelIds())) {
			if (!whereAdded) {
				query.append(" where m.id in (:modelIds) ");
				whereAdded = true;
			} else {
				query.append(" and m.id in (:modelIds) ");
			}
		}

		if (isNotNull(filters.getProducer())) {
			if (!whereAdded) {
				query.append(" where p.label = :producerLabel ");
				whereAdded = true;
			} else {
				query.append(" and p.label = :producerLabel ");
			}
		}

		if (isNotNull(filters.getProducerId())) {
			if (!whereAdded) {
				query.append(" where p.id = :producerId ");
				whereAdded = true;
			} else {
				query.append(" and p.id = :producerId ");
			}
		}

		String queryStr =  query.toString();

		if (logger.isDebugEnabled()) {
			logger.debug("queryStr " + queryStr);
		}

		Query q = getEntityManager().createQuery(queryStr);

		if (isNotNull(filters.getLabel())) {
			q.setParameter("modelLabel", filters.getLabel());
		}

		if (isNotNull(filters.getModelIds())) {
			q.setParameter("modelIds", filters.getModelIds());
		}

		if (isNotNull(filters.getProducer())) {
			q.setParameter("producerLabel", filters.getProducer());
		}

		if (isNotNull(filters.getProducerId())) {
			q.setParameter("producerId", filters.getProducerId());
		}
		// TODO extract to common
		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			q.setFirstResult((filters.getPerPage() - 1)* filters.getPage());
			q.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getPerPage())) {
			q.setMaxResults(filters.getPerPage());
		}

		List<Model> lst = (List<Model>) q.getResultList();

		if (lst.size() > 0) {
			return lst;
		} else {
			return null;
		}
	}
}
