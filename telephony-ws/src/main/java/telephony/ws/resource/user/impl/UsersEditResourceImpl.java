package telephony.ws.resource.user.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersEditResource;

/**
 * asd.
 */
public class UsersEditResourceImpl extends TelephonyServerResource
		implements UsersEditResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Put("json")
	public JsonRepresentation edit(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
