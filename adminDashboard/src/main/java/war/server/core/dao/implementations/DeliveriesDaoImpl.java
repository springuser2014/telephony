package war.server.core.dao.implementations;

import war.server.core.dao.interfaces.DeliveriesDao;
import war.server.core.entity.Delivery;

public class DeliveriesDaoImpl extends GenericDaoImpl<Delivery> implements DeliveriesDao {

    public DeliveriesDaoImpl() {
        super(Delivery.class);
    }
}
