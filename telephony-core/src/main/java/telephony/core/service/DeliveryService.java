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
    void add(SessionDto session, Delivery newDelivery,
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
    List<Delivery> find(SessionDto session, DeliveryFilterCriteria filters)
    		throws SessionServiceException, DeliveryServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param delvieryToUpdate asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    void update(SessionDto session, Delivery delvieryToUpdate)
    	throws SessionServiceException, DeliveryServiceException;

    /**
     * asd.
     * @param session TODO
     * @param deliveryToDelete asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    void delete(SessionDto session, Delivery deliveryToDelete)
    	throws SessionServiceException, DeliveryServiceException;

    /**
     * asd.
     * @param session TODO
     * @param deliveryId ads.
     * @return asd.
     * @throws DeliveryServiceException 
     * @throws SessionServiceException 
     */
	Delivery findById(SessionDto session, Long deliveryId) 
		throws SessionServiceException, DeliveryServiceException;

	/**
	 * asd.
	 * @param request asd.
	 * @return asd.
	 * @throws SessionServiceException 
	 * @throws DeliveryServiceException 
	 * @throws ParseException 
	 */
	DeliveryAddResponseDto add(DeliveryAddRequestDto request) 
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
	DeliveriesFetchResponseDto findDeliveries(DeliveriesFetchRequestDto request)
			throws SessionServiceException, DeliveryServiceException;

	DeliveryEditResponseDto edit(DeliveryEditRequestDto req) 
			throws ParseException, DeliveryServiceException, SessionServiceException;
	
	DeliveryDeleteResponseDto delete(DeliveryDeleteRequestDto req) 
			throws SessionServiceException, DeliveryServiceException;
}
