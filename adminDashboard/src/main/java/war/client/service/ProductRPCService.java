package war.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import war.server.core.configuration.Constant;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.server.core.entity.common.ProductStatus;
import war.shared.RPCServiceStatus;

import java.util.Date;
import java.util.List;

@RemoteServiceRelativePath(ProductRPCService.PATH)
public interface ProductRPCService extends RemoteService {
    
    public static String PATH = "product";
    public static String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    public List<String> fetchAllImeiInUse();
    
    public List<Product> fetchAllProducts(Long storeId, ProductStatus productStatus);
    
    public List<Product> fetchAllProductsByCriteria(String imei, String producer, String model, String color, Long storeId, Date deliveryDateStart, Date deliveryDateEnd, ProductStatus status);
    
    public Product fetchProductByImeiAndStoreId(String imei, Long storeId);
    
    public RPCServiceStatus moveProducts(Store store, List<Product> products, User user);

    public List<String> fetchAllProducers();

    public List<String> fetchAllModels();

    public List<String> fetchAllColors();

    public RPCServiceStatus updateProducts(List<Product> productsToUpdate, List<Product> productsToDelete, List<Product> productsToCancelTheSale, User editor);
}

