package telephony.ws.resource.impl;


import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class RolesResourceImpl extends TelephonyServerResource
	implements RolesResource {


	/**
	 * {@inheritDoc}
	 */
    @Put("json")
    public Representation set(JsonRepresentation entity) {
        return new JsonRepresentation("put");
    }


}
