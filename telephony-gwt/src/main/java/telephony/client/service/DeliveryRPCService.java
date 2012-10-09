package telephony.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.server.core.configuration.Constant;
import telephony.server.core.entity.Delivery;
import telephony.server.core.entity.Product;
import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;
import telephony.shared.ListOrder;
import telephony.shared.RPCServiceStatus;

import java.util.List;

@RemoteServiceRelativePath(DeliveryRPCService.PATH)
public interface DeliveryRPCService extends RemoteService {

    public static final String PATH = "delivery";
    public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;
    
    public RPCServiceStatus addNewDelivery(Delivery delivery, List<Product> products, User user, Store store);

    public List<Product> fetchDeliveriesFrom(Store store, int page, ListOrder order);
}
