package war.server.core.service.implementations;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.server.core.dao.interfaces.DeliveriesDao;
import war.server.core.dao.interfaces.ProductsDao;
import war.server.core.dao.interfaces.StoresDao;
import war.server.core.entity.Delivery;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.server.core.service.interfaces.DeliveryService;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


public class DeliveryServiceImpl implements DeliveryService {

    @Inject
    private DeliveriesDao deliveriesDao;
    
    @Inject
    private StoresDao storesDao;

    @Inject
    private ProductsDao prodctsDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private EntityManager em;


    public Delivery fetchDeliveryInfo(Long deliveryId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public void addNewDelivery(Delivery delivery, Store store, List<Product> productList, User user) {
        logger.debug("DeliveryServiceImpl.addNewDelivery starts");
                     
        em.getTransaction().begin();
        
        delivery.setCreatedAt(new Date());
        delivery.setCreatedBy(user.getId());
        
        delivery = deliveriesDao.save(delivery);
        for (Product p : productList) {
            p.setStore(store);
            p.setDelivery(delivery);
            p.setCreatedAt(new Date());
            p.setCreatedBy(user.getId());
            
            prodctsDao.save(p);
        }

        em.getTransaction().commit();

        logger.debug("DeliveryServiceImpl.addNewDelivery ends");
    }


    public List<Delivery> fetchAllDeliveries() {
        logger.debug("DeliveryServiceImpl.fetchAllDeliveries starts");

        List<Delivery> res = deliveriesDao.findUndeleted();

        logger.debug("DeliveryServiceImpl.fetchAllDeliveries ends");

        return res;
    }
}



