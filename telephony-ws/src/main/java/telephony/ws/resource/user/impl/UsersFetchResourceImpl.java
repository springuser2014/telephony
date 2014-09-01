package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersFetchResource;

/**
 * Added some stupid comment.
 */
public class UsersFetchResourceImpl 
extends TelephonyServerResource 
implements UsersFetchResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
	@Get("json")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public JsonRepresentation fetch(JsonRepresentation entity) {

        logger.info("UsersResource.get method");

        return new JsonRepresentation("users resource list");
    }
}
