package telephony.ws.resource.delivery.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesEditResource;

/**
 * asd.
 */
public class DeliveriesEditResourceImpl 
extends TelephonyServerResource 
implements DeliveriesEditResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation edit(JsonRepresentation entity) {
		
		return new JsonRepresentation("asd");
	}
}
