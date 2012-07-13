package telephony.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.server.core.configuration.Constant;
import telephony.server.core.entity.Product;
import telephony.server.core.entity.Sale;
import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;
import telephony.shared.ListOrder;
import telephony.shared.RPCServiceStatus;

import java.util.List;

@RemoteServiceRelativePath(SaleRPCService.PATH)
public interface SaleRPCService extends RemoteService {

    public static String PATH = "sale";
    public static String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    public List<Sale> fetchAllSales();

    public RPCServiceStatus addNewSale(Sale sale, List<Product> products, User user, Store store);
    
    public List<Product> fetchSalesFrom(Store store, int page, ListOrder order);

    
}
