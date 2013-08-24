package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import telephony.ws.resource.StoreRolesResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class StoreRolesResourceImpl extends ServerResource implements StoreRolesResource {

    /**
     * {@inheritDoc}
     */
    @Override
	@Put("json")
    public JsonRepresentation set(JsonRepresentation entity) {
        return new JsonRepresentation("");
    }
}
