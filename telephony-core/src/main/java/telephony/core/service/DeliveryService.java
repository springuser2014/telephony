package telephony.core.service;

import java.text.ParseException;
import java.util.List;

import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.DeliveryServiceException;

/**
 * asd.
 */
public interface DeliveryService extends BasicService<Delivery> {

	/**
	 * asd.
	 * @param request asd.
	 * @return asd.
	 * @throws telephony.core.service.exception.SessionServiceException
	 * @throws DeliveryServiceException 
	 * @throws ParseException 
	 */
	DeliveryAddResponseDto add(DeliveryAddRequest request)
			throws SessionServiceException, DeliveryServiceException, ParseException;

	/**
	 * asd.
	 * @param request a.
	 * @return d.
	 * @throws SessionServiceException a.
	 */
	DeliveryDetailsResponse fetchDetails(DeliveryDetailsRequest request)
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

	/**
	 * asd.
	 * @param req a.
	 * @return a.
	 * @throws ParseException a.
	 * @throws DeliveryServiceException a.
	 * @throws SessionServiceException a.
	 */
	DeliveryEditResponse edit(DeliveryEditRequest req)
			throws ParseException, DeliveryServiceException, SessionServiceException;
	
	/**
	 * asd.
	 * @param req a.
	 * @return a. 
	 * @throws SessionServiceException a.
	 * @throws DeliveryServiceException a.
	 */
	DeliveryDeleteResponse delete(DeliveryDeleteRequest req)
			throws SessionServiceException, DeliveryServiceException;

	////////////////////////////////
	// TODO remove the stuff below
	////////////////////////////////

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
	@Deprecated
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
	@Deprecated
    List<Delivery> find(SessionDto session, DeliveryFilterCriteria filters)
    		throws SessionServiceException, DeliveryServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param delvieryToUpdate asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    @Deprecated
    void update(SessionDto session, Delivery delvieryToUpdate)
    	throws SessionServiceException, DeliveryServiceException;

    /**
     * asd.
     * @param session TODO
     * @param deliveryToDelete asd.
     * @throws SessionServiceException asd.
     * @throws DeliveryServiceException asd.
     */
    @Deprecated
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
    @Deprecated
	Delivery findById(SessionDto session, Long deliveryId) 
		throws SessionServiceException, DeliveryServiceException;
}
