package telephony.core.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ProducerDao;
import telephony.core.entity.jpa.Producer;
import telephony.core.query.filter.ProducerFilterCriteria;

import javax.persistence.Query;

import static telephony.core.assertion.CommonAssertions.*;

/**
 * asd.
 */
public class ProducerDaoImpl 
extends GenericDaoImpl<Producer> 
implements ProducerDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * asd.
	 */
	public ProducerDaoImpl() {
		super(Producer.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Producer findByLabel(String label) {
		
		logger.debug("findByLabel starts");
        logger.debug("params : [ label : {} ]", label);

        List<Producer> res = (List<Producer>)        
        	getEntityManager()
        		.createQuery("select p from Producer p where p.label LIKE ?1")
                .setParameter(1, "%" + label + "%")                
                .getResultList();

        if (res.size() > 0) {
        	return res.get(0);
        } else {
        	return null;
        }
	}

	@Override
	public List<Producer> fetch(ProducerFilterCriteria filters) {

		logger.info("ProducersDaoImpl.fetch starts");

		StringBuilder sb = new StringBuilder();
		sb.append("select p from Producer p ");

		boolean whereAdded = false;

		if (isNotEmpty(filters.getProducerIds())) {
			sb.append(" where p.id IN (:ids) ");
			whereAdded = true;
		}

		if (isNotNull(filters.getLabel())) {
			if (whereAdded) {
				sb.append(" and p.label = :label ");
			} else {
				sb.append(" where p.label = :label ");
				whereAdded = true;
			}
		}

		Query q = getEntityManager().createQuery(sb.toString());

		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			q.setFirstResult((filters.getPerPage() - 1)* filters.getPage());
			q.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getPerPage())) {
			q.setMaxResults(filters.getPerPage());
		}

		if (isNotEmpty(filters.getProducerIds())) {
			q.setParameter("ids", filters.getProducerIds());
		}

		if (isNotNull(filters.getLabel())) {
			q.setParameter("label", filters.getLabel());
		}

		List<Producer> lst = (List<Producer>) q.getResultList();

		if (lst.size() > 0) {
			return lst;
		} else {
			return null;
		}
	}
}
