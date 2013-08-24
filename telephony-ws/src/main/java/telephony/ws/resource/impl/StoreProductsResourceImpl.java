package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import telephony.ws.resource.StoreProductsResource;
import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class StoreProductsResourceImpl extends TelephonyServerResource 
	implements StoreProductsResource {

	/**
	 * {@inheritDoc}
	 */
    @Override
	@Get("json")
    public JsonRepresentation list(JsonRepresentation entity) {
        return new JsonRepresentation("asd");

    }
}
