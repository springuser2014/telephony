package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoresModificationResource {
	
	String URL = "/stores/{id}";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation edit(JsonRepresentation entity);

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);

}