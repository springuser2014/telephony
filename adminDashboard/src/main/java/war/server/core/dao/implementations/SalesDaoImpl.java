package war.server.core.dao.implementations;

import war.server.core.dao.interfaces.SalesDao;
import war.server.core.entity.Sale;
import war.server.core.entity.Store;

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
            for(Sale sale : result)
                sale.getProducts().size();

        logger.debug("found {} elements", result.size());

        return result;
    }
}
