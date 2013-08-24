package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * ads.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface ContactsResource {

	String URL = "/contacts";
	
	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Get("json")
	JsonRepresentation get(JsonRepresentation entity);

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation post(JsonRepresentation entity);

}