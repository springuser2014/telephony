package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.UserRolesResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserRolesResourceImpl extends TelephonyServerResource
	implements UserRolesResource {

    /**
     * asd.
     * @return asd.
     */
    @Put("json")
    public JsonRepresentation put(JsonRepresentation entity) {
        return new JsonRepresentation("asd");
    }
}
