package telephony.ws.resource.store.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.store.StoreFetchProductsResource;

/**
 * asd.
 */
public class StoreFetchProductsResourceImpl 
extends TelephonyServerResource 
implements StoreFetchProductsResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Post("json")
    public JsonRepresentation list(JsonRepresentation entity) {
        return new JsonRepresentation("asd");

    }
}
