package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;

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

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    long getNumberOfDeliveries(Store store);

    /**
     * asd. 
     * @param stores asd.
     * @return asd.
     */
	List<Delivery> findByStore(Store stores);
}
