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

public interface DeliveryService extends BasicService<Delivery> {

	DeliveryAddResponse add(DeliveryAddRequest request)
			throws SessionServiceException, DeliveryServiceException;

	DeliveryDetailsResponse fetchDetails(DeliveryDetailsRequest request)
			throws SessionServiceException;

	DeliveriesFetchResponse findDeliveries(DeliveriesFetchRequest request)
			throws SessionServiceException, DeliveryServiceException;

	DeliveryEditResponse edit(DeliveryEditRequest req)
			throws ParseException, DeliveryServiceException, SessionServiceException;

	DeliveryDeleteResponse delete(DeliveryDeleteRequest req)
			throws SessionServiceException, DeliveryServiceException;

}
