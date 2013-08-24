package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;

import telephony.ws.resource.SalesModificationResource;
import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SalesModificationResourceImpl extends TelephonyServerResource
	implements SalesModificationResource {

    /**
     * {@inheritDoc}
     */
    @Put("json")
    public JsonRepresentation put(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
    }

    /**
     * {@inheritDoc}
     */
    @Delete("json")
    public JsonRepresentation delete(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
    }
}
