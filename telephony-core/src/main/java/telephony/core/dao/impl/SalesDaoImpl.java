package telephony.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.SalesDao;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.SaleFilterCriteria;

import static telephony.core.assertion.CommonAssertions.*;

/**
 * Sales management DAO.
 */
public class SalesDaoImpl 
extends GenericDaoImpl<Sale> 
implements SalesDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public SalesDaoImpl() {
        super(Sale.class);
    }

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */	
    @SuppressWarnings("unchecked")
	public List<Sale> findByStore(Store store) {
        logger.debug("SalesDaoImpl.findByStore starts");

        List<Sale> result = getEntityManager()
        		.createQuery("select s from Sale s where s.store = ?1 ")
                .setParameter(1, store)
                .getResultList();

        logger.debug("found {} elements", result.size());

        return result;
    }

	/**
	 * asd.
	 * @param store asd.
	 * @return asd.
	 */
    @Override
    public long getNumberOfSales(Store store) {
        Query query = getEntityManager().createQuery("select count(sa) from Sale sa "
                + " inner join sa.store s "
                + " where sa.deleter is null "
                + " and s.id = ?1 ")
                .setParameter(1, store.getId());

        Number res = (Number) query.getSingleResult();
        Long res2 = (Long) res;

        return res2;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findProductsBySalesIds(ArrayList<Long> ids) {
        logger.debug("SalesDaoImpl.findProductsBySalesIds starts");

        List<Product> result = getEntityManager()
        		.createQuery("select p from Product p "
                + " join fetch p.delivery d"
                + " join fetch d.store s"
                + " join fetch p.sale sa"
                + " where sa.id in (?1) "
                + " and p.deleter is null "
                + " and p.sale is not null"
                + " order by d.id desc ")
                .setParameter(1, ids)
                .getResultList();

        logger.debug(" found {} elements", result.size());

        return result;
    }

	@Override
	public Sale findByLabel(String label) {
		logger.debug("SalesDaoImpl.findProductsBySalesIds starts");

        Sale result = (Sale) getEntityManager()
        		.createQuery("select p from Sale p "
                + " join fetch p.contact c"
                + " join fetch p.store s"
                + " join fetch p.products sa"
                + " where p.label = ?1 ")
                .setParameter(1, label)
                .getSingleResult();

        return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sale> find(SaleFilterCriteria filters) {

		StringBuilder sbSubQuery1 = new StringBuilder();

		List<Long> sumMinMaxIds = null;

		// TODO : extract to method
		// TODO : add fetching only one price per product
		if (isNotNull(filters.getSumFrom()) || isNotNull(filters.getSumTo())) {
			sbSubQuery1.append(" select s2.id, sum(pr.rate) as countproducts from Product p2 ");
			sbSubQuery1.append(" join p2.sale s2 join p2.pricings pr group by s2.id ");
			sbSubQuery1.append(" having 1=1 ");

			if (isNotNull(filters.getSumFrom())) {
				sbSubQuery1.append(" and count(pr.rate) >= :sumFrom ");
			}

			if (isNotNull(filters.getSumTo())) {
				sbSubQuery1.append(" and count(pr.rate) <= :sumTo ");
			}

			Query subQuery = getEntityManager().createQuery(sbSubQuery1.toString());

			if (isNotNull(filters.getSumFrom())) {
				subQuery.setParameter("sumFrom", filters.getSumFrom());
			}

			if (isNotNull(filters.getSumTo())) {
				subQuery.setParameter("sumTo", filters.getSumTo());
			}

			List<Object[]> subResult1 = subQuery.getResultList();

			sumMinMaxIds = new ArrayList<Long>();

			for (Object[] row : subResult1) {
				sumMinMaxIds.add((Long) row[0]); // adding ids
			}
		}

		StringBuilder sbSubQuery2 = new StringBuilder();

		List<Long> numberMinMaxIds = null;

		// TODO : extract to method
		if (isNotNull(filters.getMinNumberOfProducts()) || isNotNull(filters.getMaxNumberOfProducts())) {
			sbSubQuery2.append(" select s2.id, count(s2.id) as numberofproducts from Product p2  ");
			sbSubQuery2.append(" join p2.sale s2 group by s2.id ");
			sbSubQuery2.append(" having 1=1 ");

			if (isNotNull(filters.getMinNumberOfProducts())) {
				sbSubQuery2.append(" and count(s2.id) >= :min ");
			}

			if (isNotNull(filters.getMaxNumberOfProducts())) {
				sbSubQuery2.append(" and count(s2.id) <= :max ");
			}

			Query subQuery = getEntityManager().createQuery(sbSubQuery2.toString());

			if (isNotNull(filters.getMinNumberOfProducts())) {
				subQuery.setParameter("min", new Long(filters.getMinNumberOfProducts()));
			}

			if (isNotNull(filters.getMaxNumberOfProducts())) {
				subQuery.setParameter("max", new Long(filters.getMaxNumberOfProducts()));
			}

			List<Object[]> subResult2 = subQuery.getResultList();

			numberMinMaxIds = new ArrayList<Long>();

			for (Object[] row : subResult2) {
				numberMinMaxIds.add((Long) row[0]); // adding ids
			}
		}

		StringBuilder sbMainQuery = new StringBuilder();
		sbMainQuery.append("select distinct s from Sale s ");
		sbMainQuery.append("inner join s.products p ");
		sbMainQuery.append("inner join p.model m ");
		sbMainQuery.append("inner join s.contact c ");
		sbMainQuery.append("inner join p.pricings pr ");
		sbMainQuery.append("inner join p.productTaxes pt ");
		sbMainQuery.append("inner join pt.tax t ");
		sbMainQuery.append("where 1=1 ");
		
		if (isNotNull(filters.getSoldBy())) {
			sbMainQuery.append(" and c.id = :soldBy ");
		}

		if (isNotNull(filters.getLabel())) {
			sbMainQuery.append(" and s.label = :label ");
		}

		if (isNotNull(filters.getLabelLike())) {
			sbMainQuery.append(" and s.label like :labelLike ");
		}
		
		if (isNotNull(filters.getSaleDateStart())) {
			sbMainQuery.append(" and s.dateOut >= :saleDateStart ");
		}
		
		if (isNotNull(filters.getSaleDateEnd())) {
			sbMainQuery.append(" and s.dateOut <= :saleDateEnd ");
		}
	
		if (isNotNull(numberMinMaxIds)) {
			sbMainQuery.append(" and s.id in (:numberMinMaxIds) ");
		}

		if (isNotNull(sumMinMaxIds)) {
			sbMainQuery.append(" and s.id in (:sumMinMaxIds) ");
		}
				
		Query query = getEntityManager().createQuery(sbMainQuery.toString());
		
		if (isNotNull(filters.getPage())) {
			query.setFirstResult(filters.getPage());
		}
		
		if (isNotNull(filters.getPerPage())) {
			query.setMaxResults(filters.getPerPage());
		}
		
		if (isNotNull(filters.getSaleDateEnd())) {
			query.setParameter("saleDateEnd", filters.getSaleDateEnd());
		}
		
		if (isNotNull(filters.getSaleDateStart())) {
			query.setParameter("saleDateStart", filters.getSaleDateStart());
		}
		
		if (isNotNull(filters.getSoldBy())) {
			query.setParameter("soldBy", filters.getSoldBy());
		}

		if (isNotNull(filters.getLabel())) {
			query.setParameter("label", filters.getLabel());
		}

		if (isNotNull(filters.getLabelLike())) {
			query.setParameter("labelLike", "%" + filters.getLabelLike() + "%");
		}

		if (isNotNull(numberMinMaxIds)) {
			query.setParameter("numberMinMaxIds", numberMinMaxIds);
		}

		if (isNotNull(sumMinMaxIds)) {
			query.setParameter("sumMinMaxIds", sumMinMaxIds);
		}

		List<Sale> res = (List<Sale>) query.getResultList();
		
		return res;
	}
    
}
