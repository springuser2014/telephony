package telephony.ws.resource.delivery.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesFetchResource;

/**
 * asd.
 */
public class DeliveriesFetchResourceImpl 
extends TelephonyServerResource
implements DeliveriesFetchResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation fetch(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
