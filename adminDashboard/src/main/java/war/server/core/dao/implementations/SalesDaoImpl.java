package war.server.core.dao.implementations;

import war.server.core.dao.interfaces.SalesDao;
import war.server.core.entity.Sale;

public class SalesDaoImpl extends GenericDaoImpl<Sale> implements SalesDao {

    public SalesDaoImpl() {
        super(Sale.class);
    }
}
