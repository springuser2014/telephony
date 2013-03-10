package telephony.core.dao;


import java.util.Date;
import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface ProductsDao extends GenericDao<Product> {

    /**
     * asd.
     * @param userId asd.
     * @param productStatus asd.
     * @return asd.
     */
    List<Product> findCreatedByUserId(Long userId, ProductStatus productStatus);

    /**
     * asd.
     * @param storeId asd.
     * @param productStatus asd.
     * @return asd.
     */
    List<Product> findByStoreId(Long storeId, ProductStatus productStatus);

    /**
     * asd.
     * @param imei asd.
     * @param producer asd.
     * @param model asd.
     * @param color asd.
     * @param storeId asd.
     * @param deliveryDateStart asd.
     * @param deliveryDateEnd asd.
     * @param productStatus asd.
     * @return asd.
     */
    List<Product> findByCriteria(
        String imei, String producer, String model,
        String color, Long storeId, Date deliveryDateStart,
        Date deliveryDateEnd, ProductStatus productStatus);

    /**
     * asd.
     * @param imei asd.
     * @return asd.
     */
    Product findByImei(String imei);

    /**
     * asd.
     * @return asd.
     */
    List<String> fetchImeisList();

    /**
     * asd.
     * @return asd.
     */
    List<String> fetchProducersList();

    /**
     * asd.
     * @return asd.
     */
    List<String> fetchModelsList();

    /**
     * asd.
     * @param imei asd.
     * @param storeId
     * @return asd.
     */
    Product findByImeiAndStoreId(String imei, Long storeId);
}
