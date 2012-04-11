package war.server.core.dao.interfaces;


import war.server.core.entity.Product;
import war.server.core.entity.Sale;
import war.server.core.entity.Store;

import java.util.ArrayList;
import java.util.List;

public interface SalesDao extends GenericDao<Sale>{
    List<Sale> findByStore(Store store);

    long getNumberOfSales(Store store);

    List<Sale> findLastest(Store store, int i, int numberOfElements);

    List<Product> findProductsBySalesIds(ArrayList<Long> ids);
}
