package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UsersResource {

	/**
	 * Some text.
	 */
	String URL = "/users";

	/**
	 * Some text.
	 * @param entity TODO
	 * @return asd
	 */
	@Post("json")
	JsonRepresentation add(JsonRepresentation entity);

	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 */
	@Get("json")
	JsonRepresentation list(JsonRepresentation entity);

}