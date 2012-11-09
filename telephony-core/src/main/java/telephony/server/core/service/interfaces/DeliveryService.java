package telephony.server.core.service.interfaces;


import telephony.server.core.entity.Delivery;
import telephony.server.core.entity.Product;
import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;
import telephony.shared.ListOrder;

import java.util.List;

public interface DeliveryService {

    /**
     * Zwraca szczegółowe informacje na temat dostawy
     *
     * @param deliveryId
     * @return
     */
    public Delivery fetchDeliveryInfo(Long deliveryId);

    /**
     * Dodaje nową dostawę do bazy danych według przekazanych danych
     *
     * @param delivery
     * @param store
     * @param productList
     * @param creator
     */
    public void addNewDelivery(Delivery delivery, Store store, List<Product> productList, User creator);

    /**
     * Pobiera listę zarejestrowanych dostaw
     *
     * @return
     */
    public List<Delivery> fetchAllDeliveries();


    public List<Product> fetchAllDeliveriesFrom(Store aLong, int page, ListOrder order);
}
