package telephony.core.service;

import java.util.Collection;
import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.service.dto.*;
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
    List<String> fetchAllImeiInUse(SessionDto session) 
    	throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @return asd.
     */
    List<String> fetchAllProducersInUse(SessionDto session);

    /**
     * asd.
     * @param session TODO
     * @return asd.
     * @throws SessionServiceException 
     */
    List<String> fetchAllModels(SessionDto session) 
    	throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @return asd.
     */
    List<String> fetchAllColors(SessionDto session);

    
    /**
     * asd.
     * @param session TODO
     * @param storeId asd.
     * @param productStatus asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    List<Product> fetchAllProducts(SessionDto session, Long storeId, ProductStatus productStatus) 
    	throws SessionServiceException;

    // TODO: refactor method below
    /**
     * asd.
     * @param session TODO
     * @param store asd.
     * @param products asd.
     */
    void moveProducts(SessionDto session, Store store, List<Product> products);
    
    /**
     * asd.
     * @param session TODO
     * @param imei asd.
     * @param storeId asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    Product fetchProductByImeiAndStoreId(SessionDto session, String imei, Long storeId) 
    		throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @param filters asd.
     * @return asd.
     * @throws SessionServiceException 
     */
    List<Product> findByCriteria(SessionDto session, ProductFilterCriteria filters) 
    		throws SessionServiceException;

    /**
     * asd.
     * @param session TODO
     * @param store asd.
     * @return asd.
     * @throws SessionServiceException 
     */
	List<Product> findByStore(SessionDto session, Store store) 
			throws SessionServiceException;

	/**
	 * asd.
	 * @param session TODO
	 * @param imeis asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 */
	List<Product> findByIMEIs(SessionDto session, List<String> imeis) 
			throws SessionServiceException;

	/**
	 * da.
	 * @param session TODO
	 * @param id as.
	 * @return a.
	 */
	Product findById(SessionDto session, long id);

	/**
	 * asd .
	 * @param session TODO
	 * @param coll as.
	 * @return as.
	 */
	Collection<Product> findById(SessionDto session, Collection<Long> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param product a.
	 * @return asd.
	 */
	Product update(SessionDto session, Product product);

	/**
	 * asd.
	 * @param session TODO
	 * @param imei as.
	 * @return asd.
	 */
	Product findByIMEI(SessionDto session, String imei);

	/**
	 * asdsa.
	 * @param session TODO
	 * @param coll a.
	 * @return ad.
	 */
	Collection<Product> updateCollection(SessionDto session, Collection<Product> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param product a.
	 */
	void remove(SessionDto session, Product product);

	/**
	 * asd.
	 * @param session TODO
	 * @param coll a.
	 */
	void removeCollection(SessionDto session, Collection<Product> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param coll ads.
	 */
	void removeCollectionByIds(SessionDto session, Collection<Long> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param id ads.
	 */
	void removeById(SessionDto session, long id);


	ProductFetchResponseDto find(ProductFetchRequestDto req)
			throws SessionServiceException;
}
