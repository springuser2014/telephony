package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Delivery;
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
     * @throws SessionServiceException 
     * @throws DeliveryServiceException 
     */
    void addNewDelivery(
        String username, String sessionId,
        Delivery newDelivery
    ) throws SessionServiceException, DeliveryServiceException;

    /**
     * as.
     * @param username ads.
     * @param sessionId asd.
     * @return
     */
    public List<Delivery> fetchAllDeliveries(String username, String sessionId)
    		throws SessionServiceException, DeliveryServiceException;
    
    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    void updateDelivery(String username, String sessionId, Delivery delvieryToUpdate)
    	throws SessionServiceException, DeliveryServiceException;

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @param DeliveryToDelete asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    void delete(String username, String sessionId, Delivery DeliveryToDelete)
    	throws SessionServiceException, DeliveryServiceException;

    /**
     * asd.
     * @param userame asd.
     * @param sessionId asd.
     * @param deliveryId ads.
     * @return asd.
     * @throws DeliveryServiceException 
     * @throws SessionServiceException 
     */
	Delivery findById(String userame, String sessionId, Long deliveryId) throws SessionServiceException, DeliveryServiceException;
}
