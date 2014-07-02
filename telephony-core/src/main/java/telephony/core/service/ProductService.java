package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Store;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface ProductService extends BasicService<Product> {

    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @return asd.
     * @throws SessionServiceException 
     */
    List<String> fetchAllImeiInUse(String username, String sessionId) 
    		throws SessionServiceException;

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
     * @throws SessionServiceException 
     */
    List<String> fetchAllModels(String username, String sessionId) throws SessionServiceException;

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
     * @throws SessionServiceException 
     */
    List<Product> fetchAllProducts(
    		String username, String sessionId, Long storeId, ProductStatus productStatus
    ) throws SessionServiceException;

    // TODO: refactor method below
    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @param store asd.
     * @param products asd.
     */
    void moveProducts(
    		String username, String sessionId, Store store, List<Product> products
    );

    
    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @param imei asd.
     * @param storeId asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    Product fetchProductByImeiAndStoreId(
    		String username, String sessionId, String imei, Long storeId) 
    				throws SessionServiceException;

    /**
     * asd.
     * @param username TODO
     * @param sessionId TODO
     * @param parameterObject TODO
     * @return asd.
     * @throws SessionServiceException 
     */
    List<Product> fetchAllProductsByCriteria(
        String username, String sessionId, ProductQueryCriteria parameterObject
     ) throws SessionServiceException;

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

	/**
	 * asd.
	 * @param username asd.
	 * @param sessionId asd.
	 * @param imeis asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	List<Product> findByIMEIs(String username, String sessionId, List<String> imeis) 
			throws SessionServiceException;
}
