package telephony.core.dao.interfaces;

import java.util.List;

import telephony.core.entity.Delivery;
import telephony.core.entity.Product;
import telephony.core.entity.Store;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface DeliveriesDao extends GenericDao<Delivery> {

    /**
     * asd.
     * @param ids asd.
     * @return asd.
     */
    List<Product> findProductsByDeliveriesIds(List<Long> ids);

//    public List<Delivery> findLastest(
//    Store store, int startPosition, int numberOfElements, ListOrder order);

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    long getNumberOfDeliveries(Store store);
}
