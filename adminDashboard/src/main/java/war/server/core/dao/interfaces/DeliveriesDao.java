package war.server.core.dao.interfaces;

import war.server.core.entity.Delivery;

import java.util.List;

public interface DeliveriesDao extends GenericDao<Delivery> {
    List<Delivery> findByStoreId(Long aLong);
}
