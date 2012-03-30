package war.server.core.dao.interfaces;

import war.server.core.entity.Delivery;
import war.server.core.entity.Store;

import java.util.List;

public interface DeliveriesDao extends GenericDao<Delivery> {
    List<Delivery> findByStore(Store store);
}
