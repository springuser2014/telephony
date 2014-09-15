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
		
		StringBuilder sb = new StringBuilder();
		sb.append("select DISTINCT s from Sale s ");
		sb.append("inner join s.products p ");
		sb.append("inner join p.model m ");
		sb.append("inner join s.contact c ");
		sb.append("inner join p.pricings pr ");
		sb.append("inner join p.productTaxes pt ");
		sb.append("inner join pt.tax t ");
		sb.append("where 1=1 " );
		
		if (filters.getSoldBy() != null) {
			sb.append(" and c.id = :soldBy ");
		}
		
		if (filters.getLabel() != null) {
			sb.append(" and s.label LIKE :label ");
		}
		
		if (filters.getSaleDateStart() != null) {
			sb.append(" and s.dateOut >= :saleDateStart ");
		}
		
		if (filters.getSaleDateEnd() != null) {
			sb.append(" and s.dateOut <= :saleDateEnd ");
		}
	
		if (filters.getMinNumberOfProducts() != null || 
			filters.getMaxNumberOfProducts() != null ||
			filters.getSumFrom() != null || 
			filters.getSumTo() != null) 
		{
			if (filters.getSumFrom() != null || filters.getSumTo() != null) {
				
			}
			
			sb.append(" group by s.id ");
			sb.append(" having 1=1 ");
			
			if (filters.getMaxNumberOfProducts() != null) {
				sb.append(" and count(s.id) <= :max ");
			}
			
			if (filters.getMinNumberOfProducts() != null) {
				sb.append(" and count(s.id) >= :min ");
			}
			
			if (filters.getSumFrom() != null) {
				sb.append(" and count(s.id) <= :sumFrom ");
			}
			
			if (filters.getSumTo() != null) {
				sb.append(" and sum(s.priceOut) <= :sumTo ");
			}
		}
				
		Query query = getEntityManager()
	        		.createQuery(sb.toString());
		
		if (filters.getPage() != null) {
			query.setFirstResult(filters.getPage());
		}
		
		if (filters.getPerPage() != null) {
			query.setMaxResults(filters.getPerPage());
		}
		
		if (filters.getSaleDateEnd() != null) {
			query.setParameter("saleDateEnd", filters.getSaleDateEnd());
		}
		
		if (filters.getSaleDateStart() != null) {
			query.setParameter("saleDateStart", filters.getSaleDateStart());
		}
		
		if (filters.getSoldBy() != null) {
			query.setParameter("soldBy", filters.getSoldBy());
		}
	    
		if (filters.getLabel() != null) {
			query.setParameter("label", filters.getLabel());
		}
		
		List<Sale> res = (List<Sale>) query.getResultList();
		
		return res;
	}
    
}
