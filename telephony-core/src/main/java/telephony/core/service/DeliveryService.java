package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;

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
