package telephony.ws.resource.user.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.StoreService;
import telephony.core.service.UserService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.user.UsersDeleteResource;

/**
 * asd.
 */
public class UserDeleteRolesResourceImpl extends TelephonyServerResource
		implements UsersDeleteResource {
		
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private UserService userService;

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
