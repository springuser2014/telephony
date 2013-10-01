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

/**
 * Sales management DAO.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SalesDaoImpl extends GenericDaoImpl<Sale> implements SalesDao {

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
//        		.createQuery(
//        				"select s from Sale s join fetch s.products p where s.store = ?1 ")
        		.createQuery(
        				"select s from Sale s where s.store = ?1 ")
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
    
    
}
