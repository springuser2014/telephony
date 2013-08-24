package telephony.ws.resource.impl;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import telephony.ws.resource.SalesResource;
import telephony.ws.resource.TelephonyServerResource;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SalesResourceImpl extends TelephonyServerResource 
	implements SalesResource {

    /**
     * {@inheritDoc}
     */
    @Get("json")
    public JsonRepresentation gest(JsonRepresentation entity) {
        return new JsonRepresentation("asd");
    }

    /**
     * {@inheritDoc}
     */
    @Post("json")
    public JsonRepresentation post(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
    }


}
