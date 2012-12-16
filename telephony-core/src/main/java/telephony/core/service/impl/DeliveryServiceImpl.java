package telephony.core.service.impl;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.interfaces.DeliveriesDao;
import telephony.core.dao.interfaces.ProductsDao;
import telephony.core.dao.interfaces.StoresDao;
import telephony.core.entity.Delivery;
import telephony.core.entity.Product;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.core.service.interfaces.DeliveryService;
import telephony.shared.ListOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DeliveryServiceImpl extends AbstractBasicService implements DeliveryService {

    @Inject
    private DeliveriesDao deliveriesDao;

    @Inject
    private StoresDao storesDao;

    @Inject
    private ProductsDao prodctsDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Delivery fetchDeliveryInfo(Long deliveryId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public void addNewDelivery(Delivery delivery, Store store, List<Product> productList, User user) {
        logger.debug("DeliveryServiceImpl.addNewDelivery starts");

        getEntityManager().getTransaction().begin();

        delivery.setCreatedAt(new Date());
        delivery.setCreator(user);
        delivery.setStore(store);

        delivery = deliveriesDao.save(delivery);
        for (Product p : productList) {
            p.setStore(store);
            p.setDelivery(delivery);
            p.setCreatedAt(new Date());
            p.setCreator(user);

            prodctsDao.save(p);
        }

        getEntityManager().getTransaction().commit();

        logger.debug("DeliveryServiceImpl.addNewDelivery ends");
    }


    public List<Delivery> fetchAllDeliveries() {
        logger.debug("DeliveryServiceImpl.fetchAllDeliveries starts");

        List<Delivery> res = deliveriesDao.findUndeleted();

        logger.debug("DeliveryServiceImpl.fetchAllDeliveries ends");

        return res;
    }

    public List<Product> fetchAllDeliveriesFrom(Store store, int page, ListOrder order) {
        logger.debug("DeliveryServiceImpl.fetchAllDeliveriesFrom starts");

        int numberOfElements = 6;

        List<Delivery> deliveries = deliveriesDao.findLastest(store, numberOfElements * page, numberOfElements, order);

        ArrayList<Long> ids = new ArrayList<Long>();

        for (Delivery d : deliveries) {
            ids.add(d.getId());
        }

        List<Product> result = deliveriesDao.findProductsByDeliveriesIds(ids);

        logger.debug("DeliveryServiceImpl.fetchAllDeliveriesFrom ends");

        return result;
    }
}