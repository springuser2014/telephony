package telephony.core.service.interfaces;


import java.util.List;

import telephony.core.entity.Delivery;
import telephony.core.entity.Product;
import telephony.core.entity.Store;
import telephony.core.entity.User;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface DeliveryService {

    /**
     * asd.
     *
     * @param deliveryId asd.
     * @return asd.
     */
    Delivery fetchDeliveryInfo(Long deliveryId);

    /**
     * asd.
     *
     * @param delivery asd.
     * @param store asd.
     * @param productList asd.
     * @param creator asd.
     */
    void addNewDelivery(
        Delivery delivery, Store store,
        List<Product> productList, User creator
    );

    /**
     * asd.
     *
     * @return asd.
     */
    List<Delivery> fetchAllDeliveries();

}
