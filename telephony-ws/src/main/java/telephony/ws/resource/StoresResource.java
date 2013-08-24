package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoresResource {
	
	/**
	 * asd.
	 */
	String URL = "/stores";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Get("json")
	JsonRepresentation list(JsonRepresentation entity);

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation add(JsonRepresentation entity);

}