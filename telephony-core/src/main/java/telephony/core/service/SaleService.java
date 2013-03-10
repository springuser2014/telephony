package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SaleService {

    /**
     * asd.
     * @return asd.
     */
    List<Sale> findAllSales();

    /**
     * asd.
     * @param sale asd.
     * @param products asd.
     * @param user asd.
     * @param store asd.
     */
    void addNewSale(Sale sale, List<Product> products, User user, Store store);

}
