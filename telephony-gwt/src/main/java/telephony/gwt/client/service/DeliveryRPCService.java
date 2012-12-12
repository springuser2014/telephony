package telephony.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.core.configuration.Constant;
import telephony.core.entity.Delivery;
import telephony.core.entity.Product;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.shared.ListOrder;
import telephony.shared.RPCServiceStatus;

import java.util.List;

/**
 * @todo Remove after migration to REST services
 */
@RemoteServiceRelativePath(DeliveryRPCService.PATH)
public interface DeliveryRPCService extends RemoteService {

    public static final String PATH = "delivery";
    public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    public RPCServiceStatus addNewDelivery(Delivery delivery, List<Product> products, User user, Store store);

    public List<Product> fetchDeliveriesFrom(Store store, int page, ListOrder order);
}
