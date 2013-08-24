package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.UsersResource;

/**
 * Added some stupid comment.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UsersResourceImpl extends TelephonyServerResource implements UsersResource  {

    private final Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * {@inheritDoc}
	 */
    @Override
	@Post("json")
    public final JsonRepresentation add(JsonRepresentation entity) {
        return new JsonRepresentation("users resource add");
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
	@Get("json")
    public JsonRepresentation list(JsonRepresentation entity) {

        logger.info("UsersResource.get method");

        return new JsonRepresentation("users resource list");
    }
}
