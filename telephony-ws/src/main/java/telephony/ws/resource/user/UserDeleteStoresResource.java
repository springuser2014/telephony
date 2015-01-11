package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

@Deprecated
public interface UserDeleteStoresResource {
	
	String URL = "/user/deleteStores/id";

	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);

}
