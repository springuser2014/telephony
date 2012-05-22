package war.server.core.dao.interfaces;

import war.server.core.entity.Delivery;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.shared.ListOrder;

import java.util.List;

public interface DeliveriesDao extends GenericDao<Delivery> {
    
    public List<Product> findProductsByDeliveriesIds(List<Long> ids);
    
    public List<Delivery> findLastest(Store store, int startPosition, int numberOfElements, ListOrder order);

    public long getNumberOfDeliveries(Store store);
}
