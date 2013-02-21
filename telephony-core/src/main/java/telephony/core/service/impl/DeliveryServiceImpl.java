package telephony.core.service.impl;

import java.util.Date;
import java.util.List;

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

import com.google.inject.Inject;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class DeliveryServiceImpl
    extends AbstractBasicService implements DeliveryService {

    /**
     * asd.
     */
    @Inject
    private DeliveriesDao deliveriesDao;

    /**
     * asd.
     */
    @Inject
    private StoresDao storesDao;

    /**
     * asd.
     */
    @Inject
    private ProductsDao prodctsDao;

    /**
     * asd.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     * @param deliveryId asd.
     * @return asd.
     */
    @Override
    public final Delivery fetchDeliveryInfo(final Long deliveryId) {
        return null;
    }

    // TODO : do refactoring
    /**
     * asd.
     * @param delivery asd.
     * @param store asd.
     * @param productList asd.
     * @param user asd.
     */
    @Override
    public final void addNewDelivery(
        Delivery delivery, final Store store,
        final List<Product> productList, final User user) {
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


    /**
     * asd.
     * @return asd.
     */
    @Override
    public final List<Delivery> fetchAllDeliveries() {
        logger.debug("DeliveryServiceImpl.fetchAllDeliveries starts");

        List<Delivery> res = deliveriesDao.findNotRemoved();

        logger.debug("DeliveryServiceImpl.fetchAllDeliveries ends");

        return res;
    }

    /**
     * asd.
     * @param store asd.
     * @param page asd.
     * @return asd.
     */
    public final List<Product> fetchAllDeliveriesFrom(
        final Store store, final int page) {
        logger.debug("DeliveryServiceImpl.fetchAllDeliveriesFrom starts");

//        int numberOfElements = 6;
//
//        List<Delivery> deliveries =
//        deliveriesDao.findLastest(store, numberOfElements * page,
//        numberOfElements, order);
//
//        ArrayList<Long> ids = new ArrayList<Long>();
//
//        for (Delivery d : deliveries) {
//            ids.add(d.getId());
//        }
//
//        List<Product> result = deliveriesDao.findProductsByDeliveriesIds(ids);
//
//        logger.debug("DeliveryServiceImpl.fetchAllDeliveriesFrom ends");

        return null;
    }
}
