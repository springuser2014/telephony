package war.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import war.server.core.configuration.Constant;
import war.server.core.entity.Delivery;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.shared.RPCServiceStatus;

import java.util.List;

@RemoteServiceRelativePath(DeliveryRPCService.PATH)
public interface DeliveryRPCService extends RemoteService {

    public static final String PATH = "delivery";
    public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;
    
    public RPCServiceStatus addNewDelivery(Delivery delivery, List<Product> products, User user, Store store);

    List<Delivery> fetchDeliveriesFrom(Store store);
}
