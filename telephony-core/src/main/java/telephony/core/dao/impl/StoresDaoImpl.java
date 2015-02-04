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

public class StoresDaoImpl
extends GenericDaoImpl<Store> 
implements StoresDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
	public List<Store> findByCriteria(StoreFilterCriteria filters) {
		logger.info("StoresDaoImpl.find starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", filters);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(" select s, count(s.id) as count from Store s left outer join s.products ");
		sb.append(" where 1=1 ");

		if (isNotNull(filters.getLabel())) {
			sb.append(" and where s.label = :label ");
		}

		if (isNotNull(filters.getMaxNumberOfProducts())) {
			sb.append(" and count <= :maxNumberOfProducts ");
		}

		if (isNotNull(filters.getMinNumberOfProducts())) {
			sb.append(" and count >= :minNumberOfProducts ");
		}

		if (isNotEmpty(filters.getStoreIds())) {
			sb.append(" and s.id in (:ids) ");
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
			q.setFirstResult((filters.getPerPage())* filters.getPage());
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

	@Override
	public Long countByCriteria(StoreFilterCriteria filters) {

		StringBuilder sb = new StringBuilder();
		sb.append(" select s, count(s.id) as count from Store s left outer join s.products ");
		sb.append(" where 1=1 ");

		if (isNotNull(filters.getLabel())) {
			sb.append(" and where s.label = :label ");
		}

		if (isNotNull(filters.getMaxNumberOfProducts())) {
			sb.append(" and count <= :maxNumberOfProducts ");
		}

		if (isNotNull(filters.getMinNumberOfProducts())) {
			sb.append(" and count >= :minNumberOfProducts ");
		}

		if (isNotEmpty(filters.getStoreIds())) {
			sb.append(" and s.id in (:ids) ");
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

		// TODO improve it later
		List<Long> lst = (List<Long>) q.getResultList();
		long count = 0;

		for (Long l : lst) {
			count += l;
		}

		return count;
	}
}