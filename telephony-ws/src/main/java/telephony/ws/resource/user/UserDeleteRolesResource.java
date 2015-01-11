package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

@Deprecated
public interface UserDeleteRolesResource {

	String URL = "/user/deleteRoles/id";
	
	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);
}
