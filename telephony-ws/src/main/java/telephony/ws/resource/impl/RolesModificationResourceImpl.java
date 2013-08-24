package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

import telephony.ws.resource.RolesModificationResource;
import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class RolesModificationResourceImpl extends TelephonyServerResource 
	implements RolesModificationResource {

	/**
	 * {@inheritDoc}
	 */	
	@Delete("json")
	public JsonRepresentation delete(JsonRepresentation entity) {
		
		return new JsonRepresentation("delete");
	}

}
