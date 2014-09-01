package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.UserService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UserDeleteStoresResource;

import com.google.inject.Inject;

/**
 * asd.
 */
public class UserDeleteStoresResourceImpl 
extends TelephonyServerResource
implements UserDeleteStoresResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private UserService userService;

	@Override
	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation delete(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
