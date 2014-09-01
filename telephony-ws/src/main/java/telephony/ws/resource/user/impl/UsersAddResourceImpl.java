package telephony.ws.resource.user.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersAddResource;

/**
 * asd.
 */
public class UsersAddResourceImpl 
extends TelephonyServerResource 
implements UsersAddResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
	@Post("json")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public final JsonRepresentation add(JsonRepresentation entity) {
        return new JsonRepresentation("users resource add");
    }

}
