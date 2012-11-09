package telephony.server.core.dao.implementations;

import telephony.server.core.dao.interfaces.DeliveriesDao;
import telephony.server.core.entity.Delivery;
import telephony.server.core.entity.Product;
import telephony.server.core.entity.Store;
import telephony.shared.ListOrder;

import javax.persistence.Query;
import java.util.List;

public class DeliveriesDaoImpl extends GenericDaoImpl<Delivery> implements DeliveriesDao {

    public DeliveriesDaoImpl() {
        super(Delivery.class);
    }

    public List<Product> findProductsByDeliveriesIds(List<Long> ids) {

        logger.debug("DeliveriesDaoImpl.findProductsByDeliveriesIds starts");

        List<Product> result = em.createQuery("  select p from Product p " +
                " join fetch p.delivery d" +
                " join fetch d.store s" +
                " where d.id in (?1) " +
                " and p.deleter is null " +
                " order by d.id desc ")
                .setParameter(1, ids)
                .getResultList();

        logger.debug(" found {} elements", result.size());

        return result;
    }

    public List<Delivery> findLastest(Store store, int startPosition, int numberOfElements, ListOrder order) {
        logger.info("DeliveriesDaoImpl.findLastest starts");
        logger.info("params : [ startPos : {} , numberOfElements : {} ]", startPosition, numberOfElements);
        logger.info("params : [ order : {} ]", order);

        String orderBy = "";

        if (order.equals(ListOrder.BY_DATE_ASC.toString()))
            orderBy = " order by d.dateIn asc ";

        if (order.equals(ListOrder.BY_DATE_DESC.toString()))
            orderBy = " order by d.dateIn desc ";

        if (order.equals(ListOrder.BY_TITLE_ASC.toString()))
            orderBy = " order by d.label asc ";

        if (order.equals(ListOrder.BY_TITLE_DESC.toString()))
            orderBy = " order by d.label desc";

        Query query = em.createQuery("select d from Delivery d " +
                " inner join d.store s " +
                " where d.deleter is null " +
                " and s.id = ?1 " + orderBy);

        List<Delivery> result = query.setParameter(1, store.getId())
                .setFirstResult(startPosition)
                .setMaxResults(numberOfElements)
                .getResultList();

        logger.debug("found {} elements", result.size());

        return result;
    }

    public long getNumberOfDeliveries(Store store) {
        Query query = em.createQuery("select count(d) from Delivery d " +
                " inner join d.store s " +
                " where d.deleter is null " +
                " and s.id = ?1 ")
                .setParameter(1, store.getId());

        Number res = (Number) query.getSingleResult();
        Long res2 = (Long) res;

        return res2;
    }
}
