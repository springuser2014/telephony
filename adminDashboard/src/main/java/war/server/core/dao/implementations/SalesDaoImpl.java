package war.server.core.dao.implementations;

import war.server.core.dao.interfaces.SalesDao;
import war.server.core.entity.Product;
import war.server.core.entity.Sale;
import war.server.core.entity.Store;
import war.shared.ListOrder;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class SalesDaoImpl extends GenericDaoImpl<Sale> implements SalesDao {

    public SalesDaoImpl() {
        super(Sale.class);
    }

    public List<Sale> findByStore(Store store) {
        logger.debug("SalesDaoImpl.findByStore starts");

        List<Sale> result = em.createQuery("select s from Sale s " +
                " join fetch s.products p " +
                " where s.store = ?1 " +
                " and  s.deleter is null ")
                .setParameter(1, store)
                .getResultList();

        if (result.size() > 0)
            for (Sale sale : result)
                sale.getProducts().size();

        logger.debug("found {} elements", result.size());

        return result;
    }

    public long getNumberOfSales(Store store) {
        Query query = em.createQuery("select count(sa) from Sale sa " +
                    " inner join sa.store s " +
                    " where sa.deleter is null " +
                    " and s.id = ?1 ")
                    .setParameter(1, store.getId());

        Number res = (Number) query.getSingleResult();
        Long res2 = (Long) res;

        return res2;
    }

    public List<Sale> findLastest(Store store, int startPosition, int numberOfElements, ListOrder order) {
        logger.info("SalesDaoImpl.findLastest starts");
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

        List<Sale> result = em.createQuery("select sa from Sale sa " +
                                           " inner join sa.store s " +
                                           " where sa.deleter is null " +
                                           " and s.id = ?1 " + orderBy)
                                           .setParameter(1, store.getId())
                                           .setFirstResult(startPosition)
                                           .setMaxResults(numberOfElements)
                                           .getResultList();

        logger.debug("found {} elements", result.size());

        return result;
    }

    public List<Product> findProductsBySalesIds(ArrayList<Long> ids) {
        logger.debug("SalesDaoImpl.findProductsBySalesIds starts");

        List<Product> result = em.createQuery("  select p from Product p " +
                                               " join fetch p.delivery d" +
                                               " join fetch d.store s" +
                                               " join fetch p.sale sa" +
                                               " where sa.id in (?1) " +
                                               " and p.deleter is null " +
                                               " and p.sale is not null" +
                                               " order by d.id desc ")
                                               .setParameter(1, ids)
                                               .getResultList();

        logger.debug(" found {} elements", result.size());

        return result;
    }
}
