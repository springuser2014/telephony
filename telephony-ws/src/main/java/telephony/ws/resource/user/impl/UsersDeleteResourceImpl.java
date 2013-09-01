package telephony.ws.resource.user.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersDeleteResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UsersDeleteResourceImpl extends TelephonyServerResource implements
		UsersDeleteResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Delete("json")
	public JsonRepresentation delete(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
