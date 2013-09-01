package telephony.ws.resource.role.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.role.RolesDeleteResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class RolesDeleteResourceImpl extends TelephonyServerResource 
	implements RolesDeleteResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * {@inheritDoc}
	 */	
	@Delete("json")
	public JsonRepresentation delete(JsonRepresentation entity) {
		
		return new JsonRepresentation("delete");
	}

}
