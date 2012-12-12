package telephony.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.core.configuration.Constant;
import telephony.core.entity.Product;
import telephony.core.entity.Sale;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.shared.ListOrder;
import telephony.shared.RPCServiceStatus;

import java.util.List;

/**
 * @todo Remove after migration to REST services
 */
@RemoteServiceRelativePath(SaleRPCService.PATH)
public interface SaleRPCService extends RemoteService {

    public static String PATH = "sale";
    public static String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    public List<Sale> fetchAllSales();

    public RPCServiceStatus addNewSale(Sale sale, List<Product> products, User user, Store store);

    public List<Product> fetchSalesFrom(Store store, int page, ListOrder order);


}
