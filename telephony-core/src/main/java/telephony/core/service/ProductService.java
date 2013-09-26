package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface ProductService extends BasicService<Product> {

    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     *
     * @return asd.
     */
    List<String> fetchAllImeiInUse(String username, String sessionId);

    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     *
     * @return asd.
     */
    List<String> fetchAllProducers(String username, String sessionId);

    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     *
     * @return asd.
     */
    List<String> fetchAllModels(String username, String sessionId);

    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @return asd.
     */
    List<String> fetchAllColors(String username, String sessionId);

    
    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @param storeId asd.
     * @param productStatus asd.
     * @return asd.
     */
    List<Product> fetchAllProducts(
    		String username, String sessionId, Long storeId, ProductStatus productStatus
    );

    // TODO: refactor method below
    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @param store asd.
     * @param products asd.
     * @param user asd.
     */
    void moveProducts(
    		String username, String sessionId, Store store, List<Product> products, User user
    );

    
    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @param imei asd.
     * @param storeId asd.
     * @return asd.
     */
    Product fetchProductByImeiAndStoreId(
    		String username, String sessionId, String imei, Long storeId);

    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @param parameterObject TODO
     * @return asd.
     */
    List<Product> fetchAllProductsByCriteria(
        String username, String sessionId, ProductQueryCriteria parameterObject
     );

    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @param store asd.
     * @return asd.
     * @throws SessionServiceException 
     */
	List<Product> findByStore(String username, String sessionId, Store store) 
			throws SessionServiceException;
}
