package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoreRolesResource {

	String URL = "/store/roles/{id}";

	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation set(JsonRepresentation entity);

}