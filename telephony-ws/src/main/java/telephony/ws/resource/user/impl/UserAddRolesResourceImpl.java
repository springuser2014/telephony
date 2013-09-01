package telephony.ws.resource.user.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UserAddRolesResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserAddRolesResourceImpl extends TelephonyServerResource
	implements UserAddRolesResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     * @return asd.
     */
    @Put("json")
    public JsonRepresentation add(JsonRepresentation entity) {
        return new JsonRepresentation("asd");
    }
}
