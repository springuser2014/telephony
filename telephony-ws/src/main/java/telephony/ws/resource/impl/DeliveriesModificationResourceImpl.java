package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;

import telephony.ws.resource.DeliveriesModificationResource;
import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class DeliveriesModificationResourceImpl extends
		TelephonyServerResource implements DeliveriesModificationResource {

	@Override
	@Put("json")
	public JsonRepresentation put(JsonRepresentation entity) {
		
		return new JsonRepresentation("asd");
	}

	@Override
	@Delete("json")
	public JsonRepresentation delete(JsonRepresentation entity) {
		
		return new JsonRepresentation("asd");
	}
}
