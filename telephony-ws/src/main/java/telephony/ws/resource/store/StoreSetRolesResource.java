package telephony.ws.resource.store;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

/**
 * asd.
 */
@Deprecated
public interface StoreSetRolesResource {

	String URL = "/store/setRoles/{id}";

	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation set(JsonRepresentation entity);

}