package telephony.core.service;


import java.util.Collection;
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
     * @param username asd.
     * @param sessionId asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    List<String> fetchAllImeiInUse(String username, String sessionId) 
    		throws SessionServiceException;

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     *
     * @return asd.
     */
    List<String> fetchAllProducersInUse(String username, String sessionId);

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     *
     * @return asd.
     * @throws SessionServiceException 
     */
    List<String> fetchAllModels(String username, String sessionId) throws SessionServiceException;

    /**
     * asd.
     * @param username as.
     * @param sessionId ad.
     * @return asd.
     */
    List<String> fetchAllColors(String username, String sessionId);

    
    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
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
     * @param username ad.
     * @param sessionId ad.
     * @param store asd.
     * @param products asd.
     */
    void moveProducts(
    		String username, String sessionId, Store store, List<Product> products
    );

    
    /**
     * asd.
     * @param username ad.
     * @param sessionId ad.
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
     * @param username asd.
     * @param sessionId asd.
     * @param parameterObject asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    List<Product> fetchAllProductsByCriteria(
        String username, String sessionId, ProductQueryCriteria parameterObject
     ) throws SessionServiceException;

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
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

	/**
	 * da.
	 * @param id as.
	 * @return a.
	 */
	Product findById(long id);

	/**
	 * asd .
	 * @param coll as.
	 * @return as.
	 */
	Collection<Product> findById(Collection<Long> coll);

	/**
	 * asd.
	 * @param product a.
	 * @return asd.
	 */
	Product update(Product product);

	/**
	 * asd.
	 * @param imei as.
	 * @return asd.
	 */
	Product findByIMEI(String imei);

	/**
	 * asdsa.
	 * @param coll a.
	 * @return ad.
	 */
	Collection<Product> updateCollection(Collection<Product> coll);

	/**
	 * asd.
	 * @param p a.
	 */
	void remove(Product p);

	/**
	 * asd.
	 * @param coll a.
	 */
	void removeCollection(Collection<Product> coll);

	/**
	 * asd.
	 * @param coll ads.
	 */
	void removeCollectionByIds(Collection<Long> coll);

	/**
	 * asd.
	 * @param id ads.
	 */
	void removeById(long id);
}
