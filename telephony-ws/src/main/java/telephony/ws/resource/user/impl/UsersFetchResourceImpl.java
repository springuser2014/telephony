package telephony.ws.resource.user.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersFetchResource;

/**
 * Added some stupid comment.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UsersFetchResourceImpl extends TelephonyServerResource 
	implements UsersFetchResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * {@inheritDoc}
	 */
    @Override
	@Get("json")
    public JsonRepresentation fetch(JsonRepresentation entity) {

        logger.info("UsersResource.get method");

        return new JsonRepresentation("users resource list");
    }
}
