package war.server.core.service.interfaces;


import war.server.core.entity.Delivery;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;

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


    List<Product> fetchAllDeliveriesFrom(Store aLong, int page);
}
