package telephony.ws.resource.store;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoresEditResource {
	
	String URL = "/stores/edit/{id}";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation edit(JsonRepresentation entity);

}