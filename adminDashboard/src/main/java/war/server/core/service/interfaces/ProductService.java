package war.server.core.service.interfaces;


import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.server.core.entity.common.ProductStatus;

import java.util.Date;
import java.util.List;

public interface ProductService {

    /**
     * Zwraca listę wszystkich nr IMEI który zostały już użyte.
     * (produkty są aktualnie w bazie lub zostały już kiedyś wcześniej sprzedane)
     *
     * @return
     */
    public List<String> fetchAllImeiInUse();

    /**
     * Zwraca listę nazw wszystkich producentów z bazy
     * 
     * @return
     */
    public List<String> fetchAllProducers();


    /**
     * Zwraca listę nazw wszystkich modeli produktów z bazy
     *
     * @return
     */
    public List<String> fetchAllModels();
    
    public List<String> fetchAllColors();

    /**
     * Aktualizuje listę produktów w bazie danych
     *
     * @param products
     */
    public void updateProducts(List<Product> products, User updatingUser);

    /**
     * Zwraca listę produktów według przekazanych kryteriów
     * @return
     */
    public List<Product> fetchAllProducts(Long storeId, ProductStatus productStatus);

    /**
     * Przenosi
     *
     * @param store
     * @param products
     * @param user
     */
    public void moveProducts(Store store, List<Product> products, User user);


    Product fetchProductByImeiAndStoreId(String imei, Long storeId);

    List<Product> fetchAllProductsByCriteria(String imei, String producer, String model, String color, Long storeId, Date deliveryDateStart, Date deliveryDateEnd, ProductStatus status);
}