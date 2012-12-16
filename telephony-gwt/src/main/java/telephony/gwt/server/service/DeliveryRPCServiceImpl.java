package telephony.gwt.server.service;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.gwt.client.service.DeliveryRPCService;
import telephony.shared.ListOrder;
import telephony.shared.RPCServiceStatus;
import telephony.core.entity.Delivery;
import telephony.core.entity.Product;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.core.service.interfaces.DeliveryService;
import telephony.gwt.server.gilead.GuicePersistentRemoteServiceServlet;

import java.util.List;

/**
 * @todo Remove after migration to REST services
 */
@SuppressWarnings("serial")
@Singleton
public class DeliveryRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements DeliveryRPCService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private DeliveryService deliveryService;

    @Inject
    public DeliveryRPCServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    public RPCServiceStatus addNewDelivery(Delivery delivery, List<Product> products, User user, Store store) {
        logger.debug("DeliveryRPCServiceImpl.addNewDelivery starts");

        RPCServiceStatus status = new RPCServiceStatus();

        try {
            deliveryService.addNewDelivery(delivery, store, products, user);
            status.setOperationStatusInfo("Dostawa została dodana");
            status.setStatus(RPCServiceStatus.Status.SUCCESS);
        } catch (Exception e) {
            status.setOperationStatusInfo("Wystąpił błąd podczas dodawania dostawy");
            status.setStatus(RPCServiceStatus.Status.FAILED);
            logger.error("Error during adding new delivery cause {} , message {} , stacktrace {} ", new Object[]{e.getCause(), e.getMessage(), e.getStackTrace()});
        }

        logger.debug("DeliveryRPCServiceImpl.addNewDelivery ends");

        return status;
    }

    public List<Product> fetchDeliveriesFrom(Store store, int page, ListOrder order) {

        logger.debug("DeliveryRPCServiceImpl.addNewDelivery start");
        logger.debug("params : [ storeId : {} ] ", store.getId());

        List<Product> result = deliveryService.fetchAllDeliveriesFrom(store, page, order);

        logger.debug("DeliveryRPCServiceImpl.addNewDelivery ends");

        return result;
    }
}