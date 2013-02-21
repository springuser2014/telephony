package telephony.core.service.interfaces;


import java.util.Date;
import java.util.List;

import telephony.core.entity.Product;
import telephony.core.entity.ProductStatus;
import telephony.core.entity.Store;
import telephony.core.entity.User;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface ProductService {

    /**
     * asd.
     *
     * @return asd.
     */
    List<String> fetchAllImeiInUse();

    /**
     * asd.
     *
     * @return asd.
     */
    List<String> fetchAllProducers();

    /**
     * asd.
     *
     * @return asd.
     */
    List<String> fetchAllModels();

    /**
     * asd.
     * @return asd.
     */
    List<String> fetchAllColors();

    /**
     * asd.
     *
     * @param products asd.
     * @param updatingUser asd.
     */
    void updateProducts(List<Product> products, User updatingUser);

    /**
     * asd.
     * @param storeId asd.
     * @param productStatus asd.
     * @return asd.
     */
    List<Product> fetchAllProducts(Long storeId, ProductStatus productStatus);

    /**
     * asd.
     * @param store asd.
     * @param products asd.
     * @param user asd.
     */
    void moveProducts(Store store, List<Product> products, User user);

    /**
     * asd.
     * @param imei asd.
     * @param storeId asd.
     * @return asd.
     */
    Product fetchProductByImeiAndStoreId(String imei, Long storeId);

    // TODO: refactor method below
    /**
     * asd.
     * @param imei asd.
     * @param producer asd.
     * @param model asd.
     * @param color asd.
     * @param storeId asd.
     * @param deliveryDateStart asd.
     * @param deliveryDateEnd asd.
     * @param status asd.
     * @return asd.
     */
    List<Product> fetchAllProductsByCriteria(
        String imei, String producer, String model,
        String color, Long storeId, Date deliveryDateStart,
        Date deliveryDateEnd, ProductStatus status
     );

    /**
     * asd.
     * @param productsToUpdate asd.
     * @param productsToDelete asd.
     * @param productsToCancelTheSale asd.
     * @param editor asd.
     */
    void updateProducts(
        List<Product> productsToUpdate,
        List<Product> productsToDelete,
        List<Product> productsToCancelTheSale,
        User editor
     );

    /**
     * asd.
     * @param productsToCancelTheSale asd.
     * @param editor asd.
     */
    void cancelProductsSale(List<Product> productsToCancelTheSale, User editor);
}
