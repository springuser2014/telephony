package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

@Deprecated
public interface UserAddStoresResource {

	String URL = "/user/addStores/{id}";
	
	@Put("json")
	JsonRepresentation put(JsonRepresentation entity);
}
