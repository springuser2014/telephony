package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface DeliveryService extends BasicService<Delivery>{


    /**
     * asd.
     * @param username asd.
     * @param sessionId  asd.
     * @param newDelivery asd.
     * @param products TODO
     * @param storeId TODO
     * @param contactId TODO
     * @throws SessionServiceException 
     * @throws DeliveryServiceException 
     */
    void addNewDelivery(
        String username, String sessionId,
        Delivery newDelivery, List<Product> products, Long storeId, Long contactId
    ) throws SessionServiceException, DeliveryServiceException;

    /**
     * as.
     * @param username ads.
     * @param sessionId asd.
     * @return asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    List<Delivery> fetchAllDeliveries(String username, String sessionId)
    		throws SessionServiceException, DeliveryServiceException;
    
    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @param delvieryToUpdate asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    void updateDelivery(String username, String sessionId, Delivery delvieryToUpdate)
    	throws SessionServiceException, DeliveryServiceException;

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @param deliveryToDelete asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    void delete(String username, String sessionId, Delivery deliveryToDelete)
    	throws SessionServiceException, DeliveryServiceException;

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @param deliveryId ads.
     * @return asd.
     * @throws DeliveryServiceException 
     * @throws SessionServiceException 
     */
	Delivery findById(String username, String sessionId, Long deliveryId) 
			throws SessionServiceException, DeliveryServiceException;
}
