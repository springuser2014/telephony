package telephony.core.dao.impl;

import static telephony.core.assertion.CommonAssertions.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.StoresDao;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.StoreFilterCriteria;

import javax.persistence.Query;

/**
 * Stores management DAO.
 */
public class StoresDaoImpl 
extends GenericDaoImpl<Store> 
implements StoresDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public StoresDaoImpl() {
        super(Store.class);
    }


	@Override
	public Store findByLabel(String storelabel) {
		logger.info("findByLabel starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ storelabel : {} ]", storelabel);
		}
		
		Store s = (Store) getEntityManager()
				.createQuery("select e from Store e where e.label = ?1")
				.setParameter(1, storelabel).getSingleResult();
		
		return s;
	}


	@Override
	public List<Store> find(StoreFilterCriteria filters) {
		logger.info("StoresDaoImpl.find starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", filters);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(" select s, count(s.id) as count from Store s left outer join s.products ");
		boolean whereAdded = false;

		if (isNotNull(filters.getLabel())) {
			sb.append(" where s.label = :label ");
			whereAdded = true;
		}

		if (isNotNull(filters.getMaxNumberOfProducts())) {
			if (whereAdded) {
				sb.append(" and count <= :maxNumberOfProducts ");
			} else {
				sb.append(" where count <= :maxNumberOfProducts ");
				whereAdded = true;
			}
		}

		if (isNotNull(filters.getMinNumberOfProducts())) {
			if (whereAdded) {
				sb.append(" and count >= :minNumberOfProducts ");

			} else {
				sb.append(" where count >= :minNumberOfProducts ");
				whereAdded = true;
			}
		}

		if (isNotEmpty(filters.getStoreIds())) {
			if (whereAdded) {
				sb.append(" and s.id in (:ids) ");
			} else {
				sb.append(" where s.id in (:ids) ");
				whereAdded = true;
			}
		}

		sb.append(" group by s.id ");

		Query q = getEntityManager().createQuery(sb.toString());

		if (isNotNull(filters.getLabel())) {
			q.setParameter("label", filters.getLabel());
		}

		if (isNotNull(filters.getMaxNumberOfProducts())) {
			q.setParameter("maxNumberOfProducts", filters.getMaxNumberOfProducts());
		}

		if (isNotNull(filters.getMinNumberOfProducts())) {
			q.setParameter("minNumberOfProducts", filters.getMinNumberOfProducts());
		}

		if (isNotEmpty(filters.getStoreIds())) {
			q.setParameter("ids", filters.getStoreIds());
		}

		// TODO extract to common
		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			q.setFirstResult((filters.getPerPage() - 1)* filters.getPage());
			q.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getPerPage())) {
			q.setMaxResults(filters.getPerPage());
		}

		List<Object[]> objs = (List<Object[]>) q.getResultList();
		List<Store> stores = new ArrayList<Store>();

		for (Object[] obj : objs) {
			Store store = (Store) obj[0];
			stores.add(store);
		}

		return stores;

	}
}