package telephony.ws.resource.role.impl;


import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.role.RolesAddResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class RolesAddResourceImpl extends TelephonyServerResource
	implements RolesAddResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * {@inheritDoc}
	 */
    @Post("json")
    public Representation add(JsonRepresentation entity) {
        return new JsonRepresentation("put");
    }


}
