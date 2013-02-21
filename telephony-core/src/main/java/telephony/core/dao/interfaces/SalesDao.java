package telephony.core.dao.interfaces;


import telephony.core.entity.Product;
import telephony.core.entity.Sale;
import telephony.core.entity.Store;

import java.util.ArrayList;
import java.util.List;

public interface SalesDao extends GenericDao<Sale> {

    public List<Sale> findByStore(Store store);

    public long getNumberOfSales(Store store);

//    public List<Sale> findLastest(Store store, int i, int numberOfElements, ListOrder order);

    public List<Product> findProductsBySalesIds(ArrayList<Long> ids);
}
