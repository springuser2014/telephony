package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersEditResource;

/**
 * asd.
 */
public class UsersEditResourceImpl
extends TelephonyServerResource
implements UsersEditResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation edit(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
