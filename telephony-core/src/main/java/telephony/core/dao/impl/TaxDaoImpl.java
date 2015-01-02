package telephony.core.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.TaxDao;
import telephony.core.entity.jpa.Tax;
import telephony.core.query.filter.TaxFilterCriteria;

import javax.persistence.Query;

import static  telephony.core.assertion.CommonAssertions.*;
/**
 * asd.
 */
public class TaxDaoImpl 
extends GenericDaoImpl<Tax> 
implements TaxDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * asd.
	 */
	public TaxDaoImpl() {
		super(Tax.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Tax> findInDateRange(Date from, Date to) {
        logger.info("findById starts ");

		if (logger.isDebugEnabled()) {
			logger.debug("params: [ from : {}, to : {} ]  ", from, to);
		}
        
        if (isNotNull(from) && isNotNull(to) && from.getTime() >= to.getTime()) {
        	return new ArrayList<Tax>();
        }
        
        if (isNotNull(from) && isNotNull(to))  {
        	
        	List<Tax> col = (List<Tax>) getEntityManager()
	            .createQuery("select t from Tax t where (t.from >= :from and t.to < :to) " +
						"or (t.from IS NULL and t.to < :to) " +
						"or (t.from >= :from and t.to IS NULL) ")
	            .setParameter("from", from)
	            .setParameter("to", to)
	            .getResultList();
        	
        	return col;

        } else if (isNotNull(from) && isNull(to)) {
        	
        	List<Tax> col = (List<Tax>) getEntityManager()
            .createQuery("select t from Tax t where t.from < :from")    	            		
            .setParameter("from", from)
            .getResultList();
        	
        	return col;
        	
        } else if (isNull(from) &&  isNotNull(to)) {
        	
        	List<Tax> col = (List<Tax>) getEntityManager()
            .createQuery("select t from Tax t where t.to < :to")
            .setParameter("to", to)
            .getResultList();
        	
        	return col;
            	
        } else { 
        // from == null && to == null
        	List<Tax> col = (List<Tax>) getEntityManager()
            .createQuery("select t from Tax t")
            .getResultList();
        	
        	return col;
        }        
	}

	@Override
	public List<Tax> fetch(TaxFilterCriteria filters) {
		logger.info("TaxDaoImpl.fetch starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", filters);
		}

		boolean whereAdded = false; // TODO remove this variable, introduce where 1=1 in jpql
		StringBuilder sb = new StringBuilder();
		sb.append(" select t from Tax t ");

		if (isNotNull(filters.getRateFrom())) {
			sb.append(" where t.rate >= :rateFrom ");
			whereAdded = true;
		}

		if (isNotNull(filters.getRateTo())) {
			if (whereAdded) {
				sb.append(" and t.rate < :rateTo ");
			} else {
				sb.append(" and t.rate < :rateTo ");
				whereAdded = true;
			}
		}

		if (isNotNull(filters.getTaxDateStart()) && isNotNull(filters.getTaxDateEnd())) {
			if (whereAdded) {
				sb.append(" and ((t.from >= :from and t.to < :to) ");
				sb.append(" or (t.from IS NULL and t.to < :to) ");
				sb.append(" or (t.from >= :from and t.to IS NULL)) ");
			} else {
				sb.append(" where ((t.from >= :from and t.to < :to) ");
				sb.append(" or (t.from IS NULL and t.to < :to) ");
				sb.append(" or (t.from >= :from and t.to IS NULL)) ");
				whereAdded = true;
			}
		}

		if (isNotNull(filters.getTaxDateStart()) && isNull(filters.getTaxDateEnd())) {
			if (whereAdded) {
				sb.append(" and t.from <= :from ");
			} else {
				sb.append(" where t.from <= :from ");
				whereAdded = true;
			}
		}

		if (isNull(filters.getTaxDateStart()) && isNotNull(filters.getTaxDateEnd())) {
			if (whereAdded) {
				sb.append(" and t.to < :to ");
			} else {
				sb.append(" where t.to < :to ");
				whereAdded = true;
			}
		}

		if (isNotEmpty(filters.getTaxIds())) {
			if (whereAdded) {
				sb.append(" and t.id IN (:ids) ");
		 	} else {
				sb.append(" where t.id IN (:ids) ");
				whereAdded = true;
			}
		}

		Query q = getEntityManager().createQuery(sb.toString());

		if (isNotNull(filters.getRateFrom())) {
			q.setParameter("rateFrom", filters.getRateFrom());
		}

		if (isNotNull(filters.getRateTo())) {
			q.setParameter("rateTo", filters.getRateTo());
		}

		if (isNotNull(filters.getTaxDateEnd()) && isNotNull(filters.getTaxDateStart())) {
			q.setParameter("from", filters.getTaxDateStart());
			q.setParameter("to", filters.getTaxDateEnd());
		}

		if (isNull(filters.getTaxDateEnd()) && isNotNull(filters.getTaxDateStart())) {
			q.setParameter("from", filters.getTaxDateStart());
		}

		if (isNotNull(filters.getTaxDateEnd()) && isNull(filters.getTaxDateStart())) {
			q.setParameter("to", filters.getTaxDateEnd());
		}

		if (isNotEmpty(filters.getTaxIds())) {
			q.setParameter("ids", filters.getTaxIds());
		}

		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			q.setFirstResult((filters.getPerPage() - 1)* filters.getPage());
			q.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getPerPage())) {
			q.setMaxResults(filters.getPerPage());
		}

		List<Tax> taxes = (List<Tax>) q.getResultList();

		return taxes;
	}
}
