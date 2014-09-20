package telephony.ws.resource.delivery.impl;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

import telephony.core.service.DeliveryService;
import telephony.core.service.dto.BasicResponseDto;
import telephony.core.service.dto.DeliveryEditRequestDto;
import telephony.core.service.dto.DeliveryEditResponseDto;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesEditResource;

/**
 * asd.
 */
public class DeliveriesEditResourceImpl 
extends TelephonyServerResource 
implements DeliveriesEditResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private DeliveryService deliveryService;

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation edit(DeliveryEditRequestDto entity) {
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		DeliveryEditResponseDto resp = null;
		try {
			resp = deliveryService.edit(entity);
		} catch (SessionServiceException e) {
			
			logger.error("session problem", e);
			return new JsonRepresentation(gson.toJson(new BasicResponseDto(false, "session error")));
		} catch (DeliveryServiceException e) {
			
			logger.error("internal problem", e);
			return new JsonRepresentation(gson.toJson(new BasicResponseDto(false, "internal error")));
		
		} catch(ParseException e) {
			logger.error("parsing problem", e);
			return new JsonRepresentation(gson.toJson(new BasicResponseDto(false, "parsing error")));
		}
		
		return new JsonRepresentation(gson.toJson(resp));
	}
}
