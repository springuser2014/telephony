package war.server.core.dao.interfaces;


import war.server.core.entity.Sale;
import war.server.core.entity.Store;

import java.util.List;

public interface SalesDao extends GenericDao<Sale>{
    List<Sale> findByStore(Store store);
}
