package war.server.core.dao.implementations;

import war.server.core.dao.interfaces.DeliveriesDao;
import war.server.core.entity.Delivery;
import war.server.core.entity.Store;

import java.util.List;

public class DeliveriesDaoImpl extends GenericDaoImpl<Delivery> implements DeliveriesDao {

    public DeliveriesDaoImpl() {
        super(Delivery.class);
    }

    public List<Delivery> findByStore(Store store) {
        logger.debug("DeliveriesDaoImpl.findByStore starts");

        List<Delivery> result = em.createQuery("select d from Delivery d " +
                                               " join fetch d.products " +
                                               " where d.store = ?1 " +
                                               " and  d.deleter is null ")
                                               .setParameter(1, store)
                                               .getResultList();
        
        if (result.size() > 0) 
            for(Delivery delivery : result)
                delivery.getProducts().size();


        logger.debug("found {} elements", result.size());

        return result;
    }
}
