package war.server.core.dao.interfaces;

import war.server.core.entity.Delivery;
import war.server.core.entity.Product;
import war.server.core.entity.Store;

import java.util.List;

public interface DeliveriesDao extends GenericDao<Delivery> {
    
    List<Product> findProductsByDeliveriesIds(List<Long> ids);
    
    List<Delivery> findLastest(Store store, int startPosition, int numberOfElements);

    long getNumberOfDeliveries(Store store);
}
