package war.server.core.dao.implementations;

import war.server.core.dao.interfaces.DeliveriesDao;
import war.server.core.entity.Delivery;

import java.util.List;

public class DeliveriesDaoImpl extends GenericDaoImpl<Delivery> implements DeliveriesDao {

    public DeliveriesDaoImpl() {
        super(Delivery.class);
    }

    public List<Delivery> findByStoreId(Long aLong) {
        logger.debug("DeliveriesDaoImpl.findByStoreId starts");

//        em.getTransaction().begin();


        List<Delivery> result = em.createQuery("select d from Delivery d " +
                                               " left join Product p" +
                                               " where d.store_id = ?1 ")
                                               .setParameter(1, aLong)
                                               .getResultList();


//        em.getTransaction().commit();


        logger.debug("found {} elements", result.size());

        return result;


    }
}
