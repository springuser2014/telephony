package war.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import war.server.core.configuration.Constant;
import war.server.core.entity.Product;
import war.server.core.entity.Sale;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.shared.RPCServiceStatus;

import java.util.List;

@RemoteServiceRelativePath(SaleRPCService.PATH)
public interface SaleRPCService extends RemoteService {

    public static String PATH = "sale";
    public static String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    public List<Sale> fetchAllSales();

    public RPCServiceStatus addNewSale(Sale sale, List<Product> products, User user, Store store);
    
    public List<Sale> fetchSalesFrom(Store store);

    
}
