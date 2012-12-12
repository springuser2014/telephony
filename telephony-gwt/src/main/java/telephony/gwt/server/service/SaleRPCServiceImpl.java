package telephony.gwt.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.gwt.client.service.SaleRPCService;
import telephony.core.entity.Product;
import telephony.core.entity.Sale;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.core.service.interfaces.SaleService;
import telephony.gwt.server.gilead.GuicePersistentRemoteServiceServlet;
import telephony.shared.ListOrder;
import telephony.shared.RPCServiceStatus;

import java.util.List;

/**
 * @todo Remove after migration to REST services
 */
@Singleton
public class SaleRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements SaleRPCService {

    @Inject
    private SaleService saleService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public SaleRPCServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    public List<Sale> fetchAllSales() {
        logger.debug("SaleRPCServiceImpl.fetchAllSales starts");

        List<Sale> res = saleService.findAllSales();

        logger.debug("SaleRPCServiceImpl.fetchAllSales ends");

        return res;
    }

    public RPCServiceStatus addNewSale(Sale sale, List<Product> products, User user, Store store) {
        logger.debug("SaleRPCServiceImpl.addNewSale starts");

        RPCServiceStatus result = new RPCServiceStatus();

        try {
            saleService.addNewSale(sale, products, user, store);
            result.setStatus(RPCServiceStatus.Status.SUCCESS);
            result.setOperationStatusInfo("Operacja przebiegła pomyślnie");

        } catch (Exception e) {
            result.setStatus(RPCServiceStatus.Status.FAILED);
            result.setOperationStatusInfo("Podczas realiazcji operacji wystąpił bład");
        }

        return result;
    }

    public List<Product> fetchSalesFrom(Store store, int page, ListOrder order) {
        logger.debug("SaleRPCServiceImpl.addNewSale starts");

        List<Product> result = saleService.fetchAllSalesFrom(store, page, order);

        logger.debug("SaleRPCServiceImpl.addNewSale ends");

        return result;

    }
}
