package telephony.ws.resource.delivery.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.dto.DeliveryDeleteRequest;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesDeleteResource;

/**
 * asd.
 */
public class DeliveriesDeleteResourceImpl 
extends TelephonyServerResource
implements DeliveriesDeleteResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Delete("json")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation delete(DeliveryDeleteRequest entity) {
		
		return new JsonRepresentation("asd");
	}

}
