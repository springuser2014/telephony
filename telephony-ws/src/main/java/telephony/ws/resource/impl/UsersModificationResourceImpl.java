package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.UsersModificationResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UsersModificationResourceImpl extends TelephonyServerResource
		implements UsersModificationResource {

	@Override
	@Delete("json")
	public JsonRepresentation delete(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Put("json")
	public JsonRepresentation edit(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
