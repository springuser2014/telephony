package telephony.ws.resource.delivery.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

import telephony.core.service.DeliveryService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.request.DeliveriesFetchRequestDto;
import telephony.core.service.dto.response.BasicResponseDto;
import telephony.core.service.dto.response.DeliveriesFetchResponseDto;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesFetchResource;

/**
 * asd.
 */
public class DeliveriesFetchResourceImpl 
extends TelephonyServerResource
implements DeliveriesFetchResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private DeliveryService deliveryService;
	
	@Inject
	private SessionService sessionService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation fetch(DeliveriesFetchRequestDto request) {
		
		Gson gson = new GsonBuilder().serializeNulls().create();

		DeliveriesFetchResponseDto resp;
		
		try {
			resp = deliveryService.findDeliveries(request);
			
		} catch (SessionServiceException e) {
			
			logger.error("session problem", e);
			return new JsonRepresentation(gson.toJson(new BasicResponseDto(false, "session error")));
		} catch (DeliveryServiceException e) {
			
			logger.error("internal problem", e);
			return new JsonRepresentation(gson.toJson(new BasicResponseDto(false, "internal error")));
		}
		
		return new JsonRepresentation(gson.toJson(resp));
	}
}
