package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import telephony.ws.resource.StoresResource;
import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class StoresResourceImpl extends TelephonyServerResource 
	implements StoresResource {

    /**
     * {@inheritDoc}
     */
    @Override
	@Get("json")
    public JsonRepresentation list(JsonRepresentation entity) {
        return new JsonRepresentation("asd");

    }


    /**
     * {@inheritDoc}
     */
    @Override
	@Post("json")
    public JsonRepresentation add(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");

    }


}
