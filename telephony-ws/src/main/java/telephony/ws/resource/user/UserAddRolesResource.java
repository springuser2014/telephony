package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

@Deprecated
public interface UserAddRolesResource {

	String URL = "/user/addRoles/{id}";
	
	@Put("json")
	JsonRepresentation add(JsonRepresentation entity);
}
