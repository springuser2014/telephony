package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UserAddRolesResource;

/**
 * asd.
 */
public class UserAddRolesResourceImpl 
extends TelephonyServerResource
implements UserAddRolesResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Put("json")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public JsonRepresentation add(JsonRepresentation entity) {
        return new JsonRepresentation("asd");
    }
}
