package telephony.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.DeliveriesDao;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Store;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class DeliveriesDaoImpl extends GenericDaoImpl<Delivery> implements DeliveriesDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public DeliveriesDaoImpl() {
        super(Delivery.class);
    }

    /**
     * asd.
     * @param ids asd.
     * @return asd.
     */
	@Override
    @SuppressWarnings("unchecked")
	public List<Product> findProductsByDeliveriesIds(List<Long> ids) {

        logger.debug("DeliveriesDaoImpl.findProductsByDeliveriesIds starts");

		List<Product> result =
			getEntityManager().createQuery(
        	"  select p from Product p "
            + " join fetch p.delivery d"
            + " join fetch d.store s"
            + " where d.id in (?1) "
            + " and p.deleter is null "
            + " order by d.id desc ")
            .setParameter(1, ids)
            .getResultList();

        logger.debug(" found {} elements", result.size());

        return result;
    }

	/**
	 * asd.
	 * @param store asd.
	 * @return asd.
	 */
    @Override
    public long getNumberOfDeliveries(Store store) {
        Query query = getEntityManager().createQuery("select count(d) from Delivery d "
                + " inner join d.store s "
                + " where d.deleter is null "
                + " and s.id = ?1 ")
                .setParameter(1, store.getId());

        Number res = (Number) query.getSingleResult();
        Long res2 = (Long) res;

        return res2;
    }
}
