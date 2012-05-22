package war.server.core.dao.interfaces;


import war.server.core.entity.Product;
import war.server.core.entity.Sale;
import war.server.core.entity.Store;
import war.shared.ListOrder;

import java.util.ArrayList;
import java.util.List;

public interface SalesDao extends GenericDao<Sale>{

    public List<Sale> findByStore(Store store);

    public long getNumberOfSales(Store store);

    public List<Sale> findLastest(Store store, int i, int numberOfElements, ListOrder order);

    public List<Product> findProductsBySalesIds(ArrayList<Long> ids);
}
