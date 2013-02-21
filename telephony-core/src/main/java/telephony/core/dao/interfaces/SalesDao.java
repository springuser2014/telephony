package telephony.core.dao.interfaces;


import java.util.ArrayList;
import java.util.List;

import telephony.core.entity.Product;
import telephony.core.entity.Sale;
import telephony.core.entity.Store;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SalesDao extends GenericDao<Sale> {

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    List<Sale> findByStore(Store store);

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    long getNumberOfSales(Store store);

//    public List<Sale> findLastest(Store store, int i, int numberOfElements, ListOrder order);

    /**
     * asd.
     * @param ids asd.
     * @return asd.
     */
    List<Product> findProductsBySalesIds(ArrayList<Long> ids);
}
