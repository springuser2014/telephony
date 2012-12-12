package telephony.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.core.configuration.Constant;
import telephony.core.entity.Product;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.core.entity.common.ProductStatus;
import telephony.shared.RPCServiceStatus;

import java.util.Date;
import java.util.List;

/**
 * @todo Remove after migration to REST services
 */
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

