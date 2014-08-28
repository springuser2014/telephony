package telephony.core.service;


import java.util.Collection;
import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface ProductService extends BasicService<Product> {
	

    /**
     * asd.
     * @param session TODO
     * @return asd.
     * @throws SessionServiceException 
     */
    List<String> fetchAllImeiInUse(Session session) 
    	throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @return asd.
     */
    List<String> fetchAllProducersInUse(Session session);

    /**
     * asd.
     * @param session TODO
     * @return asd.
     * @throws SessionServiceException 
     */
    List<String> fetchAllModels(Session session) 
    	throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @return asd.
     */
    List<String> fetchAllColors(Session session);

    
    /**
     * asd.
     * @param session TODO
     * @param storeId asd.
     * @param productStatus asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    List<Product> fetchAllProducts(Session session, Long storeId, ProductStatus productStatus) 
    	throws SessionServiceException;

    // TODO: refactor method below
    /**
     * asd.
     * @param session TODO
     * @param store asd.
     * @param products asd.
     */
    void moveProducts(Session session, Store store, List<Product> products);
    
    /**
     * asd.
     * @param session TODO
     * @param imei asd.
     * @param storeId asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    Product fetchProductByImeiAndStoreId(Session session, String imei, Long storeId) 
    		throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @param filters asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    List<Product> findByCriteria(Session session, ProductFilterCriteria filters) 
    		throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @param store asd.
     * @return asd.
     * @throws SessionServiceException 
     */
	List<Product> findByStore(Session session, Store store) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param imeis asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	List<Product> findByIMEIs(Session session, List<String> imeis) 
			throws SessionServiceException;

	/**
	 * da.
	 * @param session TODO
	 * @param id as.
	 * @return a.
	 */
	Product findById(Session session, long id);

	/**
	 * asd .
	 * @param session TODO
	 * @param coll as.
	 * @return as.
	 */
	Collection<Product> findById(Session session, Collection<Long> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param product a.
	 * @return asd.
	 */
	Product update(Session session, Product product);

	/**
	 * asd.
	 * @param session TODO
	 * @param imei as.
	 * @return asd.
	 */
	Product findByIMEI(Session session, String imei);

	/**
	 * asdsa.
	 * @param session TODO
	 * @param coll a.
	 * @return ad.
	 */
	Collection<Product> updateCollection(Session session, Collection<Product> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param product a.
	 */
	void remove(Session session, Product product);

	/**
	 * asd.
	 * @param session TODO
	 * @param coll a.
	 */
	void removeCollection(Session session, Collection<Product> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param coll ads.
	 */
	void removeCollectionByIds(Session session, Collection<Long> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param id ads.
	 */
	void removeById(Session session, long id);
}
