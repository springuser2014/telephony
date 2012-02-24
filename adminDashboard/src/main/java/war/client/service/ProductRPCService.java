package war.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import war.server.core.configuration.Constant;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.server.core.entity.common.ProductStatus;
import war.shared.RPCServiceStatus;

import java.util.List;

@RemoteServiceRelativePath(ProductRPCService.PATH)
public interface ProductRPCService extends RemoteService {
    
    public static String PATH = "product";
    public static String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    List<String> fetchAllImeiInUse();
    
    List<Product> fetchAllProducts(Long storeId, ProductStatus productStatus);
    
    RPCServiceStatus moveProducts(Store store, List<Product> products, User user);

    List<String> fetchAllProducers();

    List<String> fetchAllModels();

    List<String> fetchAllColors();
}

