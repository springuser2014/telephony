package telephony.core.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.ProductsDao;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.DeliveryService;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


/**
 * Deliveries management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
public class DeliveryServiceImpl
    extends AbstractBasicService<Delivery> implements DeliveryService {

    @Inject
    private DeliveriesDao deliveriesDao;

    @Inject
    private ProductsDao prodctsDao;

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
    @Transactional
    @Override
    public final void addNewDelivery(
        Delivery delivery, final Store store,
        final List<Product> productList, final User user) {
        logger.debug("DeliveryServiceImpl.addNewDelivery starts");

//        getEntityManager().getTransaction().begin();

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

//        getEntityManager().getTransaction().commit();

        logger.debug("DeliveryServiceImpl.addNewDelivery ends");
    }


    /**
     * asd.
     * @return asd.
     */
    @Override
    public final List<Delivery> fetchAllDeliveries() {
        logger.debug("DeliveryServiceImpl.fetchAllDeliveries starts");

        List<Delivery> res = deliveriesDao.find();

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

        return null;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	@Transactional
	public long count() {
		return deliveriesDao.count();
	}
}
