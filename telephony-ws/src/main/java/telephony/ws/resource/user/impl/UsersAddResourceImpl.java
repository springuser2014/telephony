package telephony.ws.resource.user.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UsersAddResourceImpl extends TelephonyServerResource implements
		UsersAddResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	/**
	 * {@inheritDoc}
	 */
    @Override
	@Post("json")
    public final JsonRepresentation add(JsonRepresentation entity) {
        return new JsonRepresentation("users resource add");
    }

}
