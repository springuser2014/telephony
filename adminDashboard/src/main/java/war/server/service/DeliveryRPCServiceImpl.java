package war.server.service;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.client.service.DeliveryRPCService;
import war.shared.RPCServiceStatus;
import war.server.core.entity.Delivery;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.server.core.service.interfaces.DeliveryService;
import war.server.gilead.GuicePersistentRemoteServiceServlet;

import java.util.List;

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

        try
        {
            deliveryService.addNewDelivery(delivery,  store, products, user);
            status.setOperationStatusInfo("Dostawa została dodana");
            status.setStatus(RPCServiceStatus.Status.SUCCESS);
        }
        catch (Exception e)
        {
            status.setOperationStatusInfo("Wystąpił błąd podczas dodawania dostawy");
            status.setStatus(RPCServiceStatus.Status.FAILED);
            logger.error("Error during adding new delivery message {}  stacktrace {} " , e.getMessage(), e.getStackTrace() );
        }

        logger.debug("DeliveryRPCServiceImpl.addNewDelivery ends");

        return status;
    }
}
