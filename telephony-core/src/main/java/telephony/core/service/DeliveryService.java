package telephony.core.service;

import java.text.ParseException;
import java.util.List;

import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.service.dto.*;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface DeliveryService extends BasicService<Delivery> {

    /**
     * asd.
     * @param session TODO
     * @param newDelivery asd.
     * @param products asd.
     * @param storeId asd.
     * @param contactId asd.
     * @throws SessionServiceException 
     * @throws DeliveryServiceException 
     */
    void add(Session session, Delivery newDelivery,
        List<Product> products, Long storeId, Long contactId)
    		throws SessionServiceException, DeliveryServiceException;

    /**
     * as.
     * @param session TODO
     * @param filters TODO
     * @return asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    List<Delivery> find(Session session, DeliveryFilterCriteria filters)
    		throws SessionServiceException, DeliveryServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param delvieryToUpdate asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    void update(Session session, Delivery delvieryToUpdate)
    	throws SessionServiceException, DeliveryServiceException;

    /**
     * asd.
     * @param session TODO
     * @param deliveryToDelete asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    void delete(Session session, Delivery deliveryToDelete)
    	throws SessionServiceException, DeliveryServiceException;

    /**
     * asd.
     * @param session TODO
     * @param deliveryId ads.
     * @return asd.
     * @throws DeliveryServiceException 
     * @throws SessionServiceException 
     */
	Delivery findById(Session session, Long deliveryId) 
		throws SessionServiceException, DeliveryServiceException;

	/**
	 * asd.
	 * @param request asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 * @throws DeliveryServiceException 
	 * @throws ParseException 
	 */
	DeliveryAddResponse add(DeliveryAddRequest request) 
			throws SessionServiceException, DeliveryServiceException, ParseException;

	/**
	 * asd.
	 * @param request a.
	 * @return d.
	 * @throws SessionServiceException a.
	 */
	DeliveryDetailsResponse findDetails(DeliveryDetailsRequest request)
			throws SessionServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return a. 
	 * @throws SessionServiceException a. 
	 * @throws DeliveryServiceException a.
	 */
	DeliveriesFetchResponse findDeliveries(DeliveriesFetchRequest request)
			throws SessionServiceException, DeliveryServiceException;

	DeliveryEditResponse edit(DeliveryEditRequest req) 
			throws ParseException, DeliveryServiceException, SessionServiceException;
}
