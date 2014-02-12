package telephony.core.dao;


import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.service.ProductQueryCriteria;


/**
 * asd.
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
     * @param store asd.
     * @return asd.
     */
    List<Product> findByStore(Store store);

    /**
     * asd.
     * @param parameterObject TODO
     * @return asd.
     */
    List<Product> findByCriteria(
        ProductQueryCriteria parameterObject);

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
     * @param storeId asd.
     * @return asd.
     */
    Product findByImeiAndStoreId(String imei, Long storeId);

    /**
     * asd.
     * @param sale asd.
     * @return asd.
     */
	List<Product> findBySale(Sale sale);

	/**
	 * asd.
	 * @param storeId ads.
	 * @param productStatus asd.
	 * @return asd.
	 */
	List<Product> findByStoreAndStatus(Long storeId, ProductStatus productStatus);

	/**
	 * asd.
	 * @param imeis asd.
	 * @return asd.
	 */
	List<Product> findByIMEIs(List<String> imeis);
}
