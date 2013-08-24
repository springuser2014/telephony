package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import telephony.ws.resource.ContactsResource;
import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
public class ContactsResourceImpl extends TelephonyServerResource
	implements ContactsResource {

	/**
	 * {@inheritDoc}
	 */
	@Get("json")
	public final JsonRepresentation get(JsonRepresentation entity) {
        return new JsonRepresentation("asd");
    }

	/**
	 * {@inheritDoc}
	 */
    @Post("json")
	public final JsonRepresentation post(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
    }
}
