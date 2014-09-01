package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

/**
 * asd.
 */
public interface UsersAddResource {

	String URL = "/users/add";

	/**
	 * {@inheritDoc}
	 */
	@Post("json")
	JsonRepresentation add(JsonRepresentation entity);

}