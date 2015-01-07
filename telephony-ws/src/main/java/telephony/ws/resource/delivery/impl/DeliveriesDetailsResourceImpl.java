package telephony.ws.resource.delivery.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.resource.Post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.service.DeliveryService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.DeliveriesFetchRequest;
import telephony.core.service.dto.request.DeliveryDetailsRequest;
import telephony.core.service.dto.response.DeliveryDetailsResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesDetailsResource;

public class DeliveriesDetailsResourceImpl 
extends TelephonyServerResource 
implements DeliveriesDetailsResource {

	@Inject
	private SessionService sessionService;
	
	@Inject
	private DeliveryService deliveryService;

	@Post("json")
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DeliveryDetailsResponse details(DeliveryDetailsRequest request) throws SessionServiceException {
		
		SessionDto session = SessionDto.create(request.getUsername(),request.getSessionId());
		
		sessionService.validate(session);
		
		DeliveryDetailsResponse resp = deliveryService.fetchDetails(request);
		
		return resp;
	}

}
