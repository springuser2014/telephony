package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.user.UserAddStoresResource;

/**
 * asd.
 */
public class UserAddStoresResourceImpl 
extends ServerResource 
implements UserAddStoresResource {

    private final Representation representation = new JsonRepresentation("");

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation put(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
