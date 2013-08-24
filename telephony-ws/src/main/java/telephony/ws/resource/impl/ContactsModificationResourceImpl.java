package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class ContactsModificationResourceImpl extends TelephonyServerResource
	implements ContactsModificationResource {


	/**
	 * {@inheritDoc}
	 */
	@Delete("json")
	public final JsonRepresentation delete(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
    }
}
