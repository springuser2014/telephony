package telephony.server.core.dao.interfaces;

import telephony.server.core.entity.Delivery;
import telephony.server.core.entity.Product;
import telephony.server.core.entity.Store;
import telephony.shared.ListOrder;

import java.util.List;

public interface DeliveriesDao extends GenericDao<Delivery> {

    public List<Product> findProductsByDeliveriesIds(List<Long> ids);

    public List<Delivery> findLastest(Store store, int startPosition, int numberOfElements, ListOrder order);

    public long getNumberOfDeliveries(Store store);
}
