package war.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.client.service.SaleRPCService;
import war.server.core.entity.Product;
import war.server.core.entity.Sale;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.server.core.service.interfaces.SaleService;
import war.server.gilead.GuicePersistentRemoteServiceServlet;
import war.shared.ListOrder;
import war.shared.RPCServiceStatus;

import java.util.List;

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
