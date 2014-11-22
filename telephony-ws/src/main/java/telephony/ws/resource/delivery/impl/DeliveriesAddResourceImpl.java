package telephony.ws.resource.delivery.impl;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.DeliveryService;
import telephony.core.service.StoreService;
import telephony.core.service.dto.request.DeliveryAddRequest;
import telephony.core.service.dto.response.BasicResponse;
import telephony.core.service.dto.response.DeliveryAddResponseDto;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesAddResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

/**
 * asd.
 */
public class DeliveriesAddResourceImpl 
extends TelephonyServerResource
implements DeliveriesAddResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    private DeliveryService deliveryService;
    
    @Inject
    private StoreService storeService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation add(DeliveryAddRequest request) {
		
		logger.info("entry point");
		DeliveryAddResponseDto resp;
		
		Gson gson = new GsonBuilder().create();

		try {
			resp = deliveryService.add(request);
		
		} catch (SessionServiceException e) {
			
			logger.error("session problem", e);
			return new JsonRepresentation(gson.toJson(new BasicResponse(false, "session error")));
		} catch (DeliveryServiceException e) {
			
			logger.error("internal problem", e);
			return new JsonRepresentation(gson.toJson(new BasicResponse(false, "internal error")));
		} catch (ParseException e) {
			
			logger.error("invalid date format", e);
			return new JsonRepresentation(gson.toJson(new BasicResponse(false, "invalid date format")));
		}
		
		logger.info("entry point");
		return new JsonRepresentation(gson.toJson(resp));
	}
}
