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
	DeliveryAddResponse add(DeliveryAddRequest request)
			throws SessionServiceException, DeliveryServiceException;

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

}
