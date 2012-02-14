package war.server.core.service.implementations;

import war.server.core.entity.Delivery;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.server.core.service.interfaces.DeliveryService;

import java.util.List;


public class DeliveryServiceImpl implements DeliveryService {
    @Override
    public Delivery fetchDeliveryInfo(Long deliveryId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addNewDelivery(Store store, List<Product> productList, User creator) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Delivery> fetchAllDeliveries() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
