package telephony.ws.resource.store.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.store.StoreSetRolesResource;

/**
 * asd.
 */
public class StoreSetRolesResourceImpl extends TelephonyServerResource
	implements StoreSetRolesResource {
	

	private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * {@inheritDoc}
     */
    @Override
	@Put("json")
    public JsonRepresentation set(JsonRepresentation entity) {
        return new JsonRepresentation("");
    }
}
